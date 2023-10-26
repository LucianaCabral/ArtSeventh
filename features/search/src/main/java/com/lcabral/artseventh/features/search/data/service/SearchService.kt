package com.lcabral.artseventh.features.search.data.service

import com.lcabral.artseventh.features.search.data.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

internal interface SearchService {
    @GET("/3/search/movie")
    suspend fun searchMovies(@Query("query") query: String): SearchResult
}
