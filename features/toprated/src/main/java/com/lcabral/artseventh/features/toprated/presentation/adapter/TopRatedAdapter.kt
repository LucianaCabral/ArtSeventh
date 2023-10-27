package com.lcabral.artseventh.features.toprated.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lcabral.artseventh.core.domain.model.Movie

internal class TopRatedAdapter(
    private val itemClicked: TopRatedItemClicked
) : ListAdapter<Movie, TopRatedViewHolder>(TopRatedDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        return TopRatedViewHolder.create(parent, itemClicked)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }
}
