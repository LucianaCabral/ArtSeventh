package com.lcabral.artseventh.features.upcoming.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.upcoming.R
import com.lcabral.artseventh.features.upcoming.databinding.ItemUpcomingBinding

internal typealias ItemClicked = (Movie) -> Unit

internal class UpcomingViewHolder(
    private val binding: ItemUpcomingBinding,
    private val itemClicked: ItemClicked
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(upcoming: Movie) {
        itemView.apply {
            with(binding) {
                upcomingMovieTv.text = upcoming.name
                Glide.with(itemView.context)
                    .load(context.getString(R.string.upcoming_uri_image) + upcoming.posterPath)
                    .into(upcomingImage)
            }
            itemView.setOnClickListener {
                itemClicked(upcoming)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup, itemClicked: ItemClicked): UpcomingViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemUpcomingBinding
                .inflate(inflater, parent, false)
            return UpcomingViewHolder(binding, itemClicked)
        }
    }
}
