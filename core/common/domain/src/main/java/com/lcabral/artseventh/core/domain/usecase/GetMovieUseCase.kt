package com.lcabral.artseventh.core.domain.usecase

import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieUseCase(private val repository: MovieRepository) {
    operator fun invoke(): Flow<List<Movie>> = repository.getMovies()
}
