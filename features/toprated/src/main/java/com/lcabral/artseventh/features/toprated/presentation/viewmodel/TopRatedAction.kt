package com.lcabral.artseventh.features.toprated.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal sealed class TopRatedAction {
    object ShowError : TopRatedAction()
    data class GoToDetails(val topRated: Movie) : TopRatedAction()

}

