package com.lcabral.artseventh.features.movies.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

internal sealed class MovieViewAction {
    object ShowError : MovieViewAction()
    data class GoToDetails(val movie: Movie) : MovieViewAction()
    data class SaveFavorite(val movie: Movie) : MovieViewAction()

}
