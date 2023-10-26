package com.lcabral.artseventh.features.movies.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lcabral.artseventh.core.domain.model.Movie

internal class MovieAdapter(
    private val itemClicked: MovieItemClicked
) : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent, itemClicked)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }
}
