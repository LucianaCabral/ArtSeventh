package com.lcabral.artseventh.features.search.data.repository

import android.util.Log
import com.lcabral.artseventh.features.search.data.source.SearchDataSource
import com.lcabral.artseventh.features.search.domain.model.Search
import com.lcabral.artseventh.features.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

internal class SearchRepositoryImpl(
    private val searchDataSource: SearchDataSource
) : SearchRepository {

    override fun searchMovies(query: String): Flow<List<Search>> {
        Log.d("<L>", "search repository: ${searchDataSource.searchMovies(query)}")
        return searchDataSource.searchMovies(query)
    }
}
