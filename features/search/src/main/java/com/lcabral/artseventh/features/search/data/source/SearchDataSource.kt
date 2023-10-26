package com.lcabral.artseventh.features.search.data.source

import com.lcabral.artseventh.features.search.domain.model.Search
import kotlinx.coroutines.flow.Flow

internal interface SearchDataSource {
    fun searchMovies(query:String): Flow<List<Search>>
}
