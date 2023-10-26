package com.lcabral.artseventh.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lcabral.artseventh.core.data.local.database.MovieDataBase.Companion.DATABASE_VERSION
import com.lcabral.artseventh.core.data.local.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = DATABASE_VERSION, exportSchema = false
)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_VERSION = 1
    }
}
