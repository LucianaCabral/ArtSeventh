package com.lcabral.artseventh.features.details.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieArgs(
    val id: Int = 0,
    val name: String = "",
    val posterPath: String = "",
    val backdropPath: String = "",
    val overview: String = "",
    val originalLanguage: String = "",
    val release: String = "",
    val popularity: Double = 0.0,
    val voteCount: Int = 0,
    val voteAverage: Double = 0.0,
    val adult: Boolean = false,
    val originalTitle: String = "",
    val video: Boolean = false
) : Parcelable
