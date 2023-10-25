package com.lcabral.artseventh.core.data.local.di


import androidx.room.Room
import com.lcabral.artseventh.core.data.local.database.MovieDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object LocalModule {
    val modules
        get() = listOf(dataModule)

    private val dataModule = module {
        single<MovieDatabase> {
            Room.databaseBuilder(
                androidContext(),
                MovieDatabase::class.java,
                "movie.db"
            ).build()
        }
    }
}


