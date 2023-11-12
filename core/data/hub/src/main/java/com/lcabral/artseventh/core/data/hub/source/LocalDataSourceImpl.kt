package com.lcabral.artseventh.core.data.hub.source

import com.lcabral.artseventh.core.data.local.database.MovieDao
import com.lcabral.artseventh.core.data.local.mapper.MovieLocalMapper
import com.lcabral.artseventh.core.data.local.model.MovieEntity
import com.lcabral.artseventh.core.data.local.source.LocalDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.libraries.arch.extensions.isNotNull
import com.lcabral.artseventh.libraries.arch.extensions.isNull
import com.lcabral.artseventh.libraries.arch.extensions.orFalse
import com.lcabral.artseventh.libraries.arch.extensions.orTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalDataSourceImpl(
    private val movieMapper: MovieLocalMapper,
    private val movieDao: MovieDao,
) : LocalDataSource {
    override suspend fun addToFavorite(movie: Movie) {
        val movieEntity = movieMapper.fromDomainToEntity(movie)
        return movieDao.insertFavorite(movieEntity)
    }

    override suspend fun isFavorite(id: Int): Boolean {
        val getFavorite = movieDao.getFavorite(id).isNotNull()
        println("<LU> isFavoriteFromLocalDataSource = $getFavorite")
        return getFavorite
    }

    override fun getFavoritesMovies(): Flow<List<Movie>> {
        return movieDao.getAllFavorites().map { movieEntities ->
            movieEntities.map { movieEntity ->
                movieMapper.fromEntityToDomain(movieEntity)
            }
        }
    }

    override suspend fun deleteFromFavorite(movie: Movie) {
        val movieEntity = movieMapper.fromDomainToEntity(movie)
        return movieDao.deleteFavorite(movieEntity)
    }
}
