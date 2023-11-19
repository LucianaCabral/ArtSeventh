package com.lcabral.artseventh.core.data.hub.source

import com.lcabral.artseventh.core.data.local.database.MovieDao
import com.lcabral.artseventh.core.data.local.mapper.toMovieEntity
import com.lcabral.artseventh.core.data.local.mapper.toMovies
import com.lcabral.artseventh.core.data.local.source.LocalDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalDataSourceImpl(
    private val movieDao: MovieDao,

) : LocalDataSource {
    override suspend fun addToFavorite(movie: Movie) {
        val setFavorite = movieDao.insertFavorite(movie.toMovieEntity())
        println("<LU> obtendo Id Salvo = ${movie.id}")
        return setFavorite
    }

    override suspend fun isFavorite(id: Int): Boolean {
        println("<LU> obtendo id do isFavorite = $id")
       return movieDao.getFavorite(id) != null
    }


//    override suspend fun isFavorite(id: Int): Boolean =
//        withContext(dispatcher) {
//            println("<LU> isFavorite = ${movieDao.getFavorite(id = id).isNotNull()}")
//            runCatching { movieDao.getFavorite(id = id).isNotNull() }
//                .onFailure { throw it }
//                .getOrThrow()
//        }

    override fun getFavoritesMovies(): Flow<List<Movie>> {
        return movieDao.getAllFavorites().map {
            it.toMovies()
        }
    }


    override suspend fun deleteFromFavorite(movie: Movie) {
        movieDao.deleteFavorite(movie.toMovieEntity())
    }
}
