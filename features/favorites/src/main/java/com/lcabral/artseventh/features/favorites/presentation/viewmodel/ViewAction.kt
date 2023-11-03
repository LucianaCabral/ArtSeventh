package com.lcabral.artseventh.features.favorites.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal sealed class ViewAction {
    object NavigateBack : ViewAction()
    object GoToDetails : ViewAction()
    data class AddToFavorites(val movie: Movie) : ViewAction()
}