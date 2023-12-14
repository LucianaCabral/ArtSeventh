package com.lcabral.artseventh.features.movies.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lcabral.artseventh.core.domain.model.Movie

internal class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
