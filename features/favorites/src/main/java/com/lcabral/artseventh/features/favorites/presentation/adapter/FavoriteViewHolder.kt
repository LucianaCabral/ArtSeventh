package com.lcabral.artseventh.features.favorites.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.IsFavoritesMoviesUseCase
import com.lcabral.artseventh.features.favorites.databinding.FavoriteItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal typealias FavoriteItemClicked = (Id: Int,Movie) -> Unit
internal class FavoriteViewHolder(
    private val binding: FavoriteItemBinding,
    private val itemClicked: FavoriteItemClicked,
    private val isFavoritesMoviesUseCase: IsFavoritesMoviesUseCase
) : RecyclerView.ViewHolder(binding.root) {

    private var job: Job? = null

    fun bindView(movie: Movie) {
        binding.apply {
            favoriteTv.text = movie.name
            Glide.with(itemView.context)
                .load(itemView.context.getString(com.lcabral.artseventh.libraries.dstools.R.string.movie_uri_image) + movie.posterPath)
                .into(imageFavorite)

            imageFavorite.setOnClickListener {
                itemClicked(imageFavorite.id, movie)
            }

            checkFavorite.setOnClickListener {
                itemClicked(checkFavorite.id, movie)
            }
            setFavorite(checkFavorite.id)
        }
    }

    private fun FavoriteItemBinding.setFavorite(id: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val isFavorite: Boolean = isFavoritesMoviesUseCase(id)
           checkFavorite.isChecked = isFavorite
            println("<LU> FavoriteViewHolder.setFavorite $isFavorite")
        }
    }

    fun jobCancel() {
        job?.cancel()
    }



    companion object {
        fun create(
            parent: ViewGroup,
            itemClicked: FavoriteItemClicked,
            isFavoritesMoviesUseCase: IsFavoritesMoviesUseCase): FavoriteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FavoriteItemBinding.inflate(inflater, parent, false)
            return FavoriteViewHolder(binding, itemClicked, isFavoritesMoviesUseCase)
        }
    }
}