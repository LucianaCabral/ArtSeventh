package com.lcabral.artseventh.core.domain.model.usecase

import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetTopRatedUseCase(private val repository: MovieRepository) {
    operator fun invoke(): Flow<List<Movie>> = repository.getTopRated()
}