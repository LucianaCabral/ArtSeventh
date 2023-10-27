package com.lcabral.artseventh.features.trendings.presentation.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal sealed class ViewAction {

    object ShowError : ViewAction()
    data class GoToDetails(val movie: Movie) : ViewAction()
}

