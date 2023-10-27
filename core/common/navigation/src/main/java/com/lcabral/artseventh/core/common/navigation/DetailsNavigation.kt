package com.lcabral.artseventh.core.common.navigation

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface DetailsNavigation {
    fun showDetails(context: Context, args: MovieArgs)
}

@Parcelize
data class MovieArgs(
    val adult: Boolean,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val name: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val backdropPath: String,
    val release: String,
    val video: Boolean,
    val voteCount: Int,
    val voteAverage: Number,
) : Parcelable
