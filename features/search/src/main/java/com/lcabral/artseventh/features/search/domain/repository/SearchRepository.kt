package com.lcabral.artseventh.features.search.domain.repository

import com.lcabral.artseventh.features.search.domain.model.Search
import kotlinx.coroutines.flow.Flow

internal interface SearchRepository {
    fun searchMovies(query:String): Flow<List<Search>>
}
