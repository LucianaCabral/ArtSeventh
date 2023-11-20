package com.lcabral.artseventh.features.favorites.presentation.viewmodel

import androidx.annotation.StringRes
import com.lcabral.artseventh.core.domain.model.Movie

internal const val LOADING_CHILD = 0
internal const val SUCCESS_CHILD = 1
internal const val FAILURE_CHILD = 2

internal data class FavoriteViewState(
    val isFavoriteChecked:Boolean = true,
    val flipperChild: Int = LOADING_CHILD,
    val getFavoritesMovies:List<Movie> = emptyList(),
    @StringRes val message: Int? = null
)
