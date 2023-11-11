package com.lcabral.artseventh.features.details.presentation.viewmodel

internal sealed class DetailViewAction {
    object NavigateBack : DetailViewAction()
    object FavoriteChecked : DetailViewAction()
}
