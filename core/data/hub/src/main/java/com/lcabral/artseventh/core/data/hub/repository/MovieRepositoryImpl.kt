package com.lcabral.artseventh.core.data.hub.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lcabral.artseventh.core.data.local.source.LocalDataSource
import com.lcabral.artseventh.core.data.remote.source.RemoteDataSource
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

internal class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val config: PagingConfig
) : MovieRepository {
    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = config,
            pagingSourceFactory = remoteDataSource::getMoviePagingSource
        ).flow
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

    override suspend fun addToFavorites(movie: Movie) {
        return localDataSource.addToFavorite(movie)
    }

    override suspend fun isFavorite(id: Int): Boolean {
        return localDataSource.isFavorite(id).apply {
        }
    }

    override fun getAllFavorites(): Flow<List<Movie>> {
        return localDataSource.getFavoritesMovies()
    }

    override suspend fun deleteFavorite(movie: Movie) {
        return localDataSource.deleteFromFavorite(movie)
    }

    override fun getDetails(): Flow<List<Movie>> {
        return remoteDataSource.getDetails()
    }
}
