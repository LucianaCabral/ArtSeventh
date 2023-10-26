package com.lcabral.artseventh.features.search.domain.model

internal data class Search(
    val adult:Boolean,
    val backdropPath: String,
    val id: Int,
    val name: String,
    val originalTitle: String,
    val overview:String,
    val originalLanguage: String,
    val posterPath: String,
    val popularity: Double,
    val release:String,
    val voteCount:Int,
    val voteAverage: Double,
    val video: Boolean
)
