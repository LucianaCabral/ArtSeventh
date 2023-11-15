package com.lcabral.artseventh.core.domain.model.usecase

import com.lcabral.artseventh.core.domain.model.repository.MovieRepository

class IsFavoritesMoviesUseCase(
    private val repository: MovieRepository) {

    suspend operator fun invoke(id:Int): Boolean {
        return repository.isFavorite(id)
    }
}