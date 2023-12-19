package com.lcabral.artseventh.features.movies.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.IsFavoritesMoviesUseCase

internal class MovieAdapter(
    private val isFavoritesMoviesUseCase: IsFavoritesMoviesUseCase,
    private val itemClicked: MovieItemClicked,

    ) : PagingDataAdapter<Movie, MovieViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent, itemClicked, isFavoritesMoviesUseCase)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position).also(holder::bindView)
    }
}
