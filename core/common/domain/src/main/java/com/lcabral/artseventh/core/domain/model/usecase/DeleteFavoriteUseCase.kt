package com.lcabral.artseventh.core.domain.model.usecase

import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.repository.MovieRepository

class DeleteFavoriteUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movie: Movie) {
        repository.deleteFavorite(movie)
    }
}