package com.lcabral.artseventh.core.data.local.model.source

import com.lcabral.artseventh.core.data.local.database.MovieDao
import com.lcabral.artseventh.core.data.local.mapper.MovieLocalMapper
import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalMovieDataSourceImpl(
    private val movieLocalMapper: MovieLocalMapper,
    private val movieDao: MovieDao
) : LocalMovieDataSource {
    override suspend fun insertMovie(movie: Movie): Long {
        val movieEntity = movieLocalMapper.fromDomainToEntity(movie)
        return movieDao.insert(movieEntity)
    }

    override fun getAll(): Flow<List<Movie>> {
        return movieDao.getAll().map { movieEntities ->
            movieEntities.map { movieEntity ->
                movieLocalMapper.fromEntityToDomain(movieEntity)
            }
        }

    }

    override suspend fun delete(movie: Movie) {
        val movieEntity = movieLocalMapper.fromDomainToEntity(movie)
        return movieDao.delete(movieEntity)
    }
}
