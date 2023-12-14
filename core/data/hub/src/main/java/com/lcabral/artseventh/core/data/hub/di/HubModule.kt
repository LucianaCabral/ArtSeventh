package com.lcabral.artseventh.core.data.hub.di

import com.lcabral.artseventh.core.data.hub.repository.MovieRepositoryImpl
import com.lcabral.artseventh.core.data.hub.source.LocalDataSourceImpl
import com.lcabral.artseventh.core.data.hub.source.RemoteDataSourceImpl
import com.lcabral.artseventh.core.data.local.database.MovieDataBase
import com.lcabral.artseventh.core.data.remote.HttpClient
import com.lcabral.artseventh.core.data.remote.service.MovieService
import com.lcabral.artseventh.core.domain.repository.MovieRepository
import org.koin.dsl.module

object HubModule {
    val modules
        get() = listOf(dataModule)
    private val dataModule = module {
        factory<MovieRepository> {
            MovieRepositoryImpl(
                remoteDataSource = RemoteDataSourceImpl(
                    service = get<HttpClient>().create(clazz = MovieService::class.java,)
                ),

                localDataSource = LocalDataSourceImpl(
                    movieDao = get<MovieDataBase>().movieDao(),
                ),
                config = get()
            )
        }
    }
}
