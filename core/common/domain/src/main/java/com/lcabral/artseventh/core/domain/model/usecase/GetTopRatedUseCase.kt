package com.lcabral.artseventh.core.domain.model.usecase

internal class GetTopRatedUseCase(private val repository: MovieRepository) {
    operator fun invoke(): Flow<List<Movie>> = repository.getTopRated()
}
