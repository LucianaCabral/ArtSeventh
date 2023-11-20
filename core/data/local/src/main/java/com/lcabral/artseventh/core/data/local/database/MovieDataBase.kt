package com.lcabral.artseventh.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lcabral.artseventh.core.data.local.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1, exportSchema = false,
)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

