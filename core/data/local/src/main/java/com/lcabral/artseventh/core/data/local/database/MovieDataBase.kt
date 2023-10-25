package com.lcabral.artseventh.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lcabral.artseventh.core.data.local.database.MovieDatabase.Companion.DATABASE_VERSION
import com.lcabral.artseventh.core.data.local.model.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = DATABASE_VERSION, exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_VERSION = 1
    }
}