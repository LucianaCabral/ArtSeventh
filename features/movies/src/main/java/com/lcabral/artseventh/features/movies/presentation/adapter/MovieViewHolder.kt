package com.lcabral.artseventh.features.movies.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.IsFavoritesMoviesUseCase
import com.lcabral.artseventh.features.movies.R
import com.lcabral.artseventh.features.movies.databinding.ItemMovieBinding
import com.lcabral.artseventh.libraries.arch.extensions.isNotNull
import com.lcabral.artseventh.libraries.arch.extensions.isNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal typealias MovieItemClicked = (Id: Int, movie: Movie) -> Unit

internal class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val itemClicked: MovieItemClicked,
    private val isFavoritesMoviesUseCase: IsFavoritesMoviesUseCase
) : RecyclerView.ViewHolder(binding.root) {

    private var job: Job? = null
    fun bindView(movie: Movie) {
        binding.apply {
            with(binding) {
                movieTv.text = movie.name
                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.movie_uri_image) + movie.posterPath)
                    .into(movieImage)
            }

            movieImage.setOnClickListener {
                itemClicked(movieImage.id, movie)
            }

            addFavoriteCheckbox.setOnClickListener {
                itemClicked(addFavoriteCheckbox.id, movie)
            }
            setFavorite(addFavoriteCheckbox.id)
        }

    }

    private fun ItemMovieBinding.setFavorite(id: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val isFavorite: Boolean = isFavoritesMoviesUseCase(id)
            addFavoriteCheckbox.isChecked = isFavorite
            println("<LU> MovieViewHolder.setFavorite $isFavorite")
        }
    }

    fun jobCancel() {
        job?.cancel()
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemClicked: MovieItemClicked,
            isFavoritesMoviesUseCase: IsFavoritesMoviesUseCase
        ): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieBinding
                .inflate(inflater, parent, false)
            return MovieViewHolder(binding, itemClicked, isFavoritesMoviesUseCase)
        }
    }
}
