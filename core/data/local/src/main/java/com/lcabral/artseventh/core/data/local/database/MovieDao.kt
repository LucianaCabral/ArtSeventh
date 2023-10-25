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
    // recebe um parametro, enytao pode receber um long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity) : Long

    //nao uso suspend pq ele já suspend  é automaticamente já que é flow
    //  STREAM OF DATA - exibe todos os filmes por id -
    @Query("SELECT * FROM movies ORDER BY id")
    fun getAll(): Flow<List<MovieEntity>>

    @Delete
    suspend fun delete(movieEntity: MovieEntity)
}
