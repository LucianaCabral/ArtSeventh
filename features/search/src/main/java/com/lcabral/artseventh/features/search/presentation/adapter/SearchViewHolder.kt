package com.lcabral.artseventh.features.search.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lcabral.artseventh.features.search.R
import com.lcabral.artseventh.features.search.databinding.ItemSearchBinding
import com.lcabral.artseventh.features.search.domain.model.Search

internal typealias SearchItemClicked = (Search) -> Unit

internal class SearchViewHolder(
    private val binding: ItemSearchBinding,
    private val itemClicked: SearchItemClicked
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(search: Search) {
        itemView.apply {
            with(binding) {
                searchTv.text = search.name
                searchImage.load(uri = context.getString(R.string.details_uri_image) + search.posterPath) {
                    placeholder(R.drawable.ic_image_movie)
                    fallback(R.drawable.ic_image_movie)
                }
            }
            itemView.setOnClickListener {
                itemClicked(search)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClicked: SearchItemClicked): SearchViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemSearchBinding
                .inflate(inflater, parent, false)
            return SearchViewHolder(binding, itemClicked)
        }
    }
}

