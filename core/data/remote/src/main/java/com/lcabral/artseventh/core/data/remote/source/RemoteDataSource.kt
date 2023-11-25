package com.lcabral.artseventh.core.data.remote.source

import androidx.paging.PagingData
import com.lcabral.artseventh.core.data.remote.paging.MoviePagingSource
import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getMovies(): Flow<List<Movie>>
    fun getPopular(): Flow<List<Movie>>
    fun getTrendings(): Flow<List<Movie>>
    fun getTopRated(): Flow<List<Movie>>
    fun upcoming(): Flow<List<Movie>>
    fun getDetails(): Flow<List<Movie>>
    fun getMoviePagingSource(): MoviePagingSource
}
