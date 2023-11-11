package com.lcabral.artseventh.core.domain.model

data class Movie(
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    val originalLanguage: String,
    val release: String,
    val popularity: Double,
    val voteCount: Int,
    val voteAverage: Double,
    val adult: Boolean,
    val originalTitle: String,
    val video: Boolean,
    var isFavorite: Boolean
)
