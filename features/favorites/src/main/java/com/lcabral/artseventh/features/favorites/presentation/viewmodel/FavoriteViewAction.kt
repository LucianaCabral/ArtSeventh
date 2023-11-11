package com.lcabral.artseventh.features.favorites.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal sealed class FavoriteViewAction {
    data class FavoriteClicked(val movie: Movie) : FavoriteViewAction()
}