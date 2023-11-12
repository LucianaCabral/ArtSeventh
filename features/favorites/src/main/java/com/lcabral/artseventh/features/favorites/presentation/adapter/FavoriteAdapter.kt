package com.lcabral.artseventh.features.favorites.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.IsFavoritesMoviesUseCase

internal class FavoriteAdapter (
    private val isFavoritesMoviesUseCase: IsFavoritesMoviesUseCase,
    private val itemClicked: FavoriteItemClicked
) : ListAdapter<Movie, FavoriteViewHolder>(FavoriteDiffCallBack()) {

    private val movies = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.create(parent, itemClicked, isFavoritesMoviesUseCase)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }


     fun removeItemRemoved(position: Int) {
      notifyItemRemoved(position)
    }
}