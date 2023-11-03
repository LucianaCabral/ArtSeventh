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

    override suspend fun setFavorite(movie: Movie): Long {
        val movieEntity = movieMapper.fromDomainToEntity(movie)
        return movieDao.setFavorite(movieEntity)
    }

    override suspend fun isFavorite(id: Int): Boolean {
           return movieDao.getFavorite(id).isNotNull()
    }

    override fun getFavorites(): Flow<List<Movie>> {
        return movieDao.getFavorites().map { movieEntities ->
            movieEntities.map { movieEntity ->
                movieMapper.fromEntityToDomain(movieEntity)
            }
        }
    }

    override suspend fun deleteFavorite(movie: Movie) {
        val movieEntity = movieMapper.fromDomainToEntity(movie)
        return movieDao.deleteFavorite(movieEntity)
    }
}
