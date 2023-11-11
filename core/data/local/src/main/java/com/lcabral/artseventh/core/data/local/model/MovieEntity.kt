package com.lcabral.artseventh.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val adult: Boolean,
    val backdropPath: String?,
    val name: String?,
    val overview: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val popularity: Double?,
    val release: String?,
    val video: Boolean?,
    val voteCount: Int?,
    val voteAverage: Double?,
    val isFavorite: Boolean?
)