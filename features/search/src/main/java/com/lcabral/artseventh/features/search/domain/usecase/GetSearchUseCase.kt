package com.lcabral.artseventh.features.search.domain.usecase

import com.lcabral.artseventh.features.search.domain.model.Search
import com.lcabral.artseventh.features.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

internal class GetSearchUseCase (
    private val searchRepository: SearchRepository
) {
    operator fun invoke(
        query: String
    ): Flow<List<Search>> {
        return searchRepository.searchMovies(query)
    }
}
