package com.lcabral.artseventh.core.domain.usecase

import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.repository.MovieRepository

class DeleteFavoriteUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) = repository.deleteFavorite(movie)
}
