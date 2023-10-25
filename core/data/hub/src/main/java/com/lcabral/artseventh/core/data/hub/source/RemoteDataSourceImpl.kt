package com.lcabral.artseventh.core.data.hub.source


import com.lcabral.artseventh.core.data.remote.source.RemoteDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

internal class RemoteDataSourceImpl(
    private val remoteDataSource: RemoteDataSource
) : RemoteDataSource {

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
