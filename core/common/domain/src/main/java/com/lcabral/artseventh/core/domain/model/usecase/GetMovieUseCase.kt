package com.lcabral.artseventh.core.domain.model.usecase

import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieUseCase (
    private val repository: MovieRepository,
) {
    operator fun invoke(): Flow<List<Movie>> {
        return repository.getMovies()
    }
}
