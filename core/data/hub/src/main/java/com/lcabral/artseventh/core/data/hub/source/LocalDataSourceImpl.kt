package com.lcabral.artseventh.core.data.hub.source

import com.lcabral.artseventh.core.data.local.database.MovieDao
import com.lcabral.artseventh.core.data.local.mapper.toMovieEntity
import com.lcabral.artseventh.core.data.local.mapper.toMovies
import com.lcabral.artseventh.core.data.local.source.LocalDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.libraries.arch.extensions.isNotNull
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

internal class LocalDataSourceImpl(
    private val movieDao: MovieDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

) : LocalDataSource {
    override suspend fun addToFavorite(movie: Movie) {
        val setFavorite = movieDao.insertFavorite(movie.toMovieEntity())
        println("<LU> setFavoriteFromLocalDataSource = ${movie.toMovieEntity()}")
        return setFavorite
    }

    override suspend fun isFavorite(id: Int): Boolean {
        val getFavorite = movieDao.getFavorite(id).isNotNull()
        runCatching {
            println("<LU> isFavoriteFromLocalDataSource = $getFavorite")
        }
            .onSuccess { movieDao.getFavorite(id).isNotNull() }
            .onFailure { IllegalStateException() }
        return getFavorite
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
