package com.lcabral.artseventh.features.popular.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.popular.databinding.ItemPopularBinding

internal typealias ItemClicked = (Movie) -> Unit

internal class PopularViewHolder(
    private val binding: ItemPopularBinding,
    private val itemClicked: ItemClicked
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(popular: Movie) {
        itemView.apply {
            with(binding) {
                popularMovieTv.text = popular.name
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${popular.posterPath}").into(popularImage)
            }
        }
        itemView.setOnClickListener {
            itemClicked(popular)
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClicked: ItemClicked): PopularViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPopularBinding
                .inflate(inflater, parent, false)
            return PopularViewHolder(binding, itemClicked)
        }
    }
}
