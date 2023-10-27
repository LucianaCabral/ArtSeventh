package com.lcabral.artseventh.features.upcoming.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lcabral.artseventh.core.domain.model.Movie

internal class UpcomingAdapter(
    private val itemClicked: ItemClicked
) : ListAdapter<Movie, UpcomingViewHolder>(UpcomingDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
       return UpcomingViewHolder.create(parent, itemClicked)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }
}

