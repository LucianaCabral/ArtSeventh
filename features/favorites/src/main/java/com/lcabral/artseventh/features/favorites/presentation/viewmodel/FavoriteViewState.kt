package com.lcabral.artseventh.features.favorites.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal data class FavoriteViewState(
    val isFavoriteChecked:Boolean = true,
    val isLoading:Boolean = false,
    val movies:List<Movie> = emptyList()
)
