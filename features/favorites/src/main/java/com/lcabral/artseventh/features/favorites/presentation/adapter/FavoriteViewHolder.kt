package com.lcabral.artseventh.features.favorites.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.favorites.databinding.ItemMovieBinding

internal typealias FavoriteItemClicked = (Movie) -> Unit

internal class FavoriteViewHolder(
    private val binding: ItemMovieBinding,
    private val itemClicked: FavoriteItemClicked
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(movie: Movie) {
        itemView.apply {
            with(binding) {
                favoriteTv.text = movie.name
                Glide.with(itemView.context)
                    .load(context.getString(com.lcabral.artseventh.libraries.dstools.R.string.movie_uri_image) + movie.posterPath).into(favoriteImage)
            }
        }
        itemView.setOnClickListener {
            itemClicked(movie)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClicked: FavoriteItemClicked): FavoriteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieBinding
                .inflate(inflater, parent, false)
            return FavoriteViewHolder(binding, itemClicked)
        }
    }
}