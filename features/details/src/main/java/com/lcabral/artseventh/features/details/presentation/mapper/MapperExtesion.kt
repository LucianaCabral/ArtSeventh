package com.lcabral.artseventh.features.details.presentation.mapper

import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.features.details.presentation.model.MovieArgs

internal fun Movie.toMovieArgs() =
    MovieArgs(
        adult = adult,
        id = id,
        backdropPath = backdropPath,
        name = name,
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        posterPath = posterPath,
        popularity = popularity,
        release = release,
        voteAverage = voteAverage,
        voteCount = voteCount,
        video = video
    )

internal fun MovieArgs.toMovie() =
    Movie(
        adult = adult,
        id = id,
        backdropPath = backdropPath,
        name = name,
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        posterPath = posterPath,
        popularity = popularity,
        release = release,
        voteAverage = voteAverage,
        voteCount = voteCount,
        video = video
    )


