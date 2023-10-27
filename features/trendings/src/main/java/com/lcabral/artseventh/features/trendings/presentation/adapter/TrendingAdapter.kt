package com.lcabral.artseventh.features.trendings.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lcabral.artseventh.core.domain.model.Movie

internal class TrendingAdapter(
    private val itemClicked: ItemClicked
) : ListAdapter<Movie, TrendingViewHolder>(TrendingDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder.create(parent, itemClicked)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }
}
