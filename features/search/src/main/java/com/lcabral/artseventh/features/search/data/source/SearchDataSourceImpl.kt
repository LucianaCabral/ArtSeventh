package com.lcabral.artseventh.features.search.data.source

import com.lcabral.artseventh.features.search.data.model.toSearch
import com.lcabral.artseventh.features.search.data.service.SearchService
import com.lcabral.artseventh.features.search.domain.model.Search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class SearchDataSourceImpl(
    private val service: SearchService
) : SearchDataSource {

    override fun searchMovies(query: String): Flow<List<Search>> {
        return flow {
            emit(service.searchMovies(query).toSearch())
        }
    }
}
