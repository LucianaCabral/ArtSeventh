package com.lcabral.artseventh.core.data.local.model.source

import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface LocalMovieDataSource {
    suspend fun insertMovie(movie: Movie): Long
    fun getAll(): Flow<List<Movie>>
    suspend fun delete(movie: Movie)
}