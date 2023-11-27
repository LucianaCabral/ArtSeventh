package com.lcabral.artseventh.features.details.presentation.mapper

import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.libraries.arch.extensions.orFalse

internal fun MovieArgs?.toMovie() =
    Movie(
        adult = this?.adult.orFalse(),
        id = this?.id ?: 0,
        backdropPath = this?.backdropPath.orEmpty(),
        name = this?.overview.orEmpty(),
        originalLanguage = this?.originalLanguage.orEmpty(),
        originalTitle = this?.originalTitle.orEmpty(),
        posterPath = this?.posterPath.orEmpty(),
        popularity = this?.popularity ?: 0.0,
        release = this?.release.orEmpty(),
        voteAverage = this?.voteAverage ?: 0.0,
        voteCount = this?.voteCount ?: 0,
        video = this?.video.orFalse(),
        overview = this?.overview.orEmpty()
    )


