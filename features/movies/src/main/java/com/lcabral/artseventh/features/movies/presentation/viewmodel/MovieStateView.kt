package com.lcabral.artseventh.features.movies.presentation.viewmodel

import androidx.annotation.StringRes
import com.lcabral.artseventh.core.domain.model.Movie

internal const val LOADING_CHILD = 0
internal const val SUCCESS_CHILD = 1
internal const val FAILURE_CHILD = 2

internal data class MovieStateView(
    val getMoviesResultItems: List<Movie>? = null,
    val flipperChild: Int = LOADING_CHILD,
    var errorMessage: String? = null,
    @StringRes val message: Int? = null
)
