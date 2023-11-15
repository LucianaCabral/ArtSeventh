package com.lcabral.artseventh.features.favorites.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.favorites.databinding.FavoriteItemBinding

internal typealias FavoriteItemClicked = (Id: Int,Movie) -> Unit
internal class FavoriteViewHolder(
    private val binding: FavoriteItemBinding,
    private val itemClicked: FavoriteItemClicked,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(movie: Movie) {
        binding.apply {
            favoriteTv.text = movie.name
            Glide.with(itemView.context)
                .load(itemView.context.getString(com.lcabral.artseventh.libraries.dstools.R.string.movie_uri_image)
                        + movie.posterPath)
                .into(imageFavorite)

            imageFavorite.setOnClickListener {
                itemClicked(imageFavorite.id, movie)
            }

            checkFavorite.setOnClickListener {
                itemClicked(checkFavorite.id, movie)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            itemClicked: FavoriteItemClicked) : FavoriteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = FavoriteItemBinding.inflate(inflater, parent, false)
            return FavoriteViewHolder(binding, itemClicked)
        }
    }
}
