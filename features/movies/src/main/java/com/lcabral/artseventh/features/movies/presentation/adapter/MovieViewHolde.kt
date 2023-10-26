package com.lcabral.artseventh.features.movies.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.movies.R
import com.lcabral.artseventh.features.movies.databinding.ItemMovieBinding

internal typealias MovieItemClicked = (Movie) -> Unit

internal class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val itemClicked: MovieItemClicked
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(movie: Movie) {
        itemView.apply {
            with(binding) {
                movieTv.text = movie.name
                Glide.with(itemView.context)
                    .load(context.getString(R.string.movie_uri_image) + movie.posterPath).into(movieImage)
            }
        }
        itemView.setOnClickListener {
            itemClicked(movie)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClicked: MovieItemClicked): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMovieBinding
                .inflate(inflater, parent, false)
            return MovieViewHolder(binding, itemClicked)
        }
    }
}
