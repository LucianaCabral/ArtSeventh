package com.lcabral.artseventh.features.trendings.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.trendings.databinding.ItemTrendingCarouselBinding

internal typealias ItemClicked = (Movie) -> Unit

internal class TrendingViewHolder(
    private val binding: ItemTrendingCarouselBinding,
    private val itemClicked: ItemClicked
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(trending: Movie) {
        itemView.apply {
            with(binding) {
                trendingMovieTv.text = trending.name
                Glide.with(itemView.context).load(
                    context.getString(com.lcabral.artseventh.libraries.dstools.R.string.movie_uri_image)
                            + trending.backdropPath
                ).into(trendingImage)
            }
        }
        itemView.setOnClickListener {
            itemClicked(trending)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClicked: ItemClicked): TrendingViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemTrendingCarouselBinding
                .inflate(inflater, parent, false)
            return TrendingViewHolder(binding, itemClicked)
        }
    }
}
