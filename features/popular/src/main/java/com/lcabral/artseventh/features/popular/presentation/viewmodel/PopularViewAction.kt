package com.lcabral.artseventh.features.popular.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal sealed class PopularViewAction {
    object ShowError: PopularViewAction()
    data class GoToDetails(val popular: Movie) : PopularViewAction()

}
