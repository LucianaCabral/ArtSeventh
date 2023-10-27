package com.lcabral.artseventh.features.upcoming.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal sealed class UpcomingViewAction {
    object ShowError : UpcomingViewAction()
    data class GoToDetails(val movie: Movie) : UpcomingViewAction()
}

