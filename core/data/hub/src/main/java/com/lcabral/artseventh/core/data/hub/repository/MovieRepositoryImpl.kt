package com.lcabral.artseventh.core.data.hub.repository

import com.lcabral.artseventh.core.data.remote.source.RemoteDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

internal class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): MovieRepository{
    override fun getMovies(): Flow<List<Movie>> {
        return remoteDataSource.getMovies()
    }

    override fun getPopular(): Flow<List<Movie>> {
        return remoteDataSource.getPopular()
    }

    override fun getTrendings(): Flow<List<Movie>> {
        return remoteDataSource.getTrendings()
    }

    override fun getTopRated(): Flow<List<Movie>> {
        return remoteDataSource.getTopRated()
    }

    override fun upcoming(): Flow<List<Movie>> {
        return remoteDataSource.upcoming()
    }
}
