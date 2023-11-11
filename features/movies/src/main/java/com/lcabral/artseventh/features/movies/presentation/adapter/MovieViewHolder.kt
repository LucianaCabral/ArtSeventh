package com.lcabral.artseventh.features.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.movies.R
import com.lcabral.artseventh.features.movies.databinding.ItemMovieBinding

internal typealias MovieItemClicked = (Movie) -> Unit
internal typealias FavoriteItemClicked = (Movie) -> Unit

internal class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val itemClicked: MovieItemClicked,
//    private val favoriteClicked: FavoriteItemClicked
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(movie: Movie) {
        binding.apply {
            with(binding) {
                movieTv.text = movie.name
                Glide.with(itemView.context)
                    .load(itemView.context.getString(R.string.movie_uri_image) + movie.posterPath)
                    .into(movieImage)
                addFavoriteCheckbox.isChecked = movie.isFavorite
            }

            movieImage.setOnClickListener {
                itemClicked(movie)
            }
//            addFavoriteCheckbox.setOnClickListener {
//                movie.isFavorite = addFavoriteCheckbox.isChecked
//                favoriteClicked(movie)
//            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemClicked: MovieItemClicked,
//            favoriteClicked: FavoriteItemClicked
        ): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieBinding
                .inflate(inflater, parent, false)
            return MovieViewHolder(binding, itemClicked)
        }
    }
}
