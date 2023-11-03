package com.lcabral.artseventh.core.domain.model.repository

import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    fun getPopular(): Flow<List<Movie>>
    fun getTrendings(): Flow<List<Movie>>
    fun getTopRated(): Flow<List<Movie>>
    fun upcoming(): Flow<List<Movie>>
    suspend fun setFavorite(movie:Movie): Long
    suspend fun isFavorite(id:Int): Boolean
    fun getFavorites(): Flow<List<Movie>>
    suspend fun deleteFavorite(movie: Movie)
}
