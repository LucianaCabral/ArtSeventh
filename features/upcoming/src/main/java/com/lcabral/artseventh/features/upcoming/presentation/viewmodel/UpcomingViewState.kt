package com.lcabral.artseventh.features.upcoming.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal const val LOADING_CHILD = 0
internal const val SUCCESS_CHILD = 1
internal const val FAILURE_CHILD = 2
internal data class UpcomingViewState(
    val getUpcomingResultItems: List<Movie>? = null,
    val flipperChild: Int = LOADING_CHILD
)
