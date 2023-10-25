package com.lcabral.artseventh.core.domain.model.repository

import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    fun getPopular(): Flow<List<Movie>>
    fun getTrendings(): Flow<List<Movie>>
    fun getTopRated(): Flow<List<Movie>>
    fun upcoming(): Flow<List<Movie>>
    suspend fun insertMovie(movie:Movie): Long
    fun getAll(): Flow<List<Movie>>
    suspend fun delete(movie: Movie)


}
