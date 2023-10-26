package com.lcabral.artseventh.features.search.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lcabral.artseventh.features.search.domain.model.Search

internal class SearchDiffCallBack : DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.
        posterPath == newItem.posterPath
    }
}
