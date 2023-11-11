package com.lcabral.artseventh.core.data.hub.source

import com.lcabral.artseventh.core.data.local.database.MovieDao
import com.lcabral.artseventh.core.data.local.mapper.MovieLocalMapper
import com.lcabral.artseventh.core.data.local.source.LocalDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.libraries.arch.extensions.isNotNull
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
