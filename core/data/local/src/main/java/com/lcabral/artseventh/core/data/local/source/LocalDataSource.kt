package com.lcabral.artseventh.core.data.local.source

import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun setFavorite(movie: Movie): Long
    suspend fun isFavorite(id:Int): Boolean
    fun getFavorites(): Flow<List<Movie>>
    suspend fun deleteFavorite(movie: Movie)
}
