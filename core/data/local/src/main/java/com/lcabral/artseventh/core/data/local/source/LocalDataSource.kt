package com.lcabral.artseventh.core.data.local.source

import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addToFavorite(movie: Movie)
//    suspend fun isFavorite(id:Int): Boolean
    fun getFavoritesMovies(): Flow<List<Movie>>
    suspend fun deleteFromFavorite(movie: Movie)
}
