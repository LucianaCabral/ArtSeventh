package com.lcabral.artseventh.features.toprated.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.toprated.R
import com.lcabral.artseventh.features.toprated.databinding.ItemTopRatedBinding

internal typealias TopRatedItemClicked = (Movie) -> Unit

internal class TopRatedViewHolder(
    private val binding: ItemTopRatedBinding,
    private val itemClicked: TopRatedItemClicked
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(topRated: Movie) {
        itemView.apply {
            with(binding) {
                topRatedMovieTv.text = topRated.name
                Glide.with(itemView.context)
                    .load(context.getString(R.string.top_rated_uri_image) + topRated.backdropPath)
                    .into(topRatedImage)
            }
        }
        itemView.setOnClickListener {
            itemClicked(topRated)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClicked: TopRatedItemClicked): TopRatedViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemTopRatedBinding
                .inflate(inflater, parent, false)
            return TopRatedViewHolder(binding, itemClicked)
        }
    }
}

