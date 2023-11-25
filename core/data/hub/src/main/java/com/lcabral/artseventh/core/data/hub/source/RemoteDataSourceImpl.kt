package com.lcabral.artseventh.core.data.hub.source

import com.lcabral.artseventh.core.data.remote.model.toMovie
import com.lcabral.artseventh.core.data.remote.paging.MoviePagingSource
import com.lcabral.artseventh.core.data.remote.service.MovieService
import com.lcabral.artseventh.core.data.remote.source.RemoteDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class RemoteDataSourceImpl(
    private val service: MovieService
) : RemoteDataSource {

    override fun getMovies(): Flow<List<Movie>> {
        return flow { emit(service.getMovie().toMovie()) }
    }

    override fun getPopular(): Flow<List<Movie>> {
        return flow { emit(service.getPopular().toMovie()) }
    }

    override fun getTrendings(): Flow<List<Movie>> {
        return flow { emit(service.getTrendings().toMovie()) }
    }

    override fun getTopRated(): Flow<List<Movie>> {
        return flow { emit(service.getTopRated().toMovie()) }
    }

    override fun upcoming(): Flow<List<Movie>> {
        return flow { emit(service.getUpcoming().toMovie()) }
    }

    override fun getDetails(): Flow<List<Movie>> {
        return flow { emit(service.getDetails().toMovie()) }
    }

    override fun getMoviePagingSource(): MoviePagingSource {
        return MoviePagingSource(remoteDataSource = this)
    }
}
