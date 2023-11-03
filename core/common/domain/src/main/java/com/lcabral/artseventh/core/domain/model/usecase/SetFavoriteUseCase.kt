package com.lcabral.artseventh.core.domain.model.usecase

import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.repository.MovieRepository

class SetFavoriteUseCase(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(movie:Movie): Long {
        return repository.setFavorite(movie)
    }
}