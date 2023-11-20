package com.lcabral.artseventh.core.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lcabral.artseventh.core.data.local.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getFavorite(id: Int): MovieEntity?

    @Query("SELECT * FROM movies")
    fun getAllFavorites(): Flow<List<MovieEntity>>

    @Delete
    suspend fun deleteFavorite(movieEntity: MovieEntity)
}
