package com.lcabral.artseventh.features.details.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal sealed class ViewAction {
    object NavigateBack : ViewAction()
    object GoToDetails : ViewAction()
    data class SaveToFavorite(val movie: Long): ViewAction()
    object FavoriteClicked : ViewAction()
}
