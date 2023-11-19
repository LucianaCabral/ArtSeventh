package com.lcabral.artseventh.core.domain.repository

import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    fun getPopular(): Flow<List<Movie>>
    fun getTrendings(): Flow<List<Movie>>
    fun getTopRated(): Flow<List<Movie>>
    fun upcoming(): Flow<List<Movie>>
    suspend fun addToFavorites(movie:Movie)
    suspend fun isFavorite(id:Int): Boolean
    fun getAllFavorites(): Flow<List<Movie>>
    suspend fun deleteFavorite(movie: Movie)

    fun getDetails(): Flow<List<Movie>>
}
