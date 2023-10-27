package com.lcabral.artseventh.features.search.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lcabral.artseventh.features.search.domain.model.Search

internal class SearchAdapter(
    private val itemClicked: SearchItemClicked
) : ListAdapter<Search, SearchViewHolder>(SearchDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.create(parent, itemClicked)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }
}
