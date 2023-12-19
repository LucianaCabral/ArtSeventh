package com.lcabral.artseventh.core.domain.usecase

import androidx.paging.PagingData
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetDetailsUseCase(private val repository: MovieRepository) {
    operator fun invoke(): Flow<PagingData<Movie>> = repository.getMovies()
}
