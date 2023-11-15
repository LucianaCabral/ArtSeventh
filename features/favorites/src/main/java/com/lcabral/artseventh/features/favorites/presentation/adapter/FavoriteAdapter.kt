package com.lcabral.artseventh.features.favorites.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lcabral.artseventh.core.domain.model.Movie

internal class FavoriteAdapter (
    private val itemClicked: FavoriteItemClicked
) : ListAdapter<Movie, FavoriteViewHolder>(FavoriteDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.create(parent, itemClicked)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }
}
