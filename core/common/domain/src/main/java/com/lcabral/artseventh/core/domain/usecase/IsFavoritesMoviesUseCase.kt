package com.lcabral.artseventh.core.domain.usecase

import com.lcabral.artseventh.core.domain.repository.MovieRepository

class IsFavoritesMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(id:Int): Boolean = repository.isFavorite(id)
}
