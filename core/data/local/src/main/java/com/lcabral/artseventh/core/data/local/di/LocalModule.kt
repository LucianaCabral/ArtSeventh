package com.lcabral.artseventh.core.data.local.di

import androidx.room.Room
import com.lcabral.artseventh.core.data.local.database.MovieDao
import com.lcabral.artseventh.core.data.local.database.MovieDataBase
import org.koin.dsl.module

object LocalModule {
    val modules
        get() = listOf(dataModule)

    private val dataModule = module {
        single<MovieDataBase> {
            Room.databaseBuilder(
                context = get(),
                MovieDataBase::class.java,
                "movie.db"
            ).build()
        }

        single<MovieDao> {
            get<MovieDataBase>().movieDao()
        }
    }
}


