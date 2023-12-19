package com.lcabral.artseventh.core.data.remote.source

import com.lcabral.artseventh.core.data.remote.paging.MoviePagingSource
import com.lcabral.artseventh.core.data.remote.model.MovieResponse
import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getMovies(page:Int): MovieResponse
    fun getPopular(): Flow<List<Movie>>
    fun getTrendings(): Flow<List<Movie>>
    fun getTopRated(): Flow<List<Movie>>
    fun upcoming(): Flow<List<Movie>>
    fun getDetails(): Flow<List<Movie>>
    fun getMoviePagingSource(): MoviePagingSource
}
