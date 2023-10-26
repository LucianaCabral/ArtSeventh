package com.lcabral.artseventh.core.data.hub.source

import com.lcabral.artseventh.core.data.local.database.MovieDao
import com.lcabral.artseventh.core.data.local.mapper.MovieLocalMapper
import com.lcabral.artseventh.core.data.local.source.LocalDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalDataSourceImpl(
    private val movieMapper: MovieLocalMapper,
    private val movieDao: MovieDao
) : LocalDataSource {

    override suspend fun insertMovie(movie: Movie): Long {
        val movieEntity = movieMapper.fromDomainToEntity(movie)
        return movieDao.insert(movieEntity)
    }

    override fun getAll(): Flow<List<Movie>> {
        return movieDao.getAll().map { movieEntities ->
            movieEntities.map { movieEntity ->
                movieMapper.fromEntityToDomain(movieEntity)
            }
        }
    }

    override suspend fun delete(movie: Movie) {
        val movieEntity = movieMapper.fromDomainToEntity(movie)
        return movieDao.delete(movieEntity)
    }
}
