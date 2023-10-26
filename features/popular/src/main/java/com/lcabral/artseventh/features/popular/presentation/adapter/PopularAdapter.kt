package com.lcabral.artseventh.features.popular.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lcabral.artseventh.core.domain.model.Movie

internal class PopularAdapter(
    private val itemClicked: ItemClicked
) : ListAdapter<Movie, PopularViewHolder>(PopularDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
       return PopularViewHolder.create(parent, itemClicked = itemClicked)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }
}
