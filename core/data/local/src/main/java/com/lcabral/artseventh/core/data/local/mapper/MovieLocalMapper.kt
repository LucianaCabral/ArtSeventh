package com.lcabral.artseventh.core.data.local.mapper

import com.lcabral.artseventh.core.data.local.model.MovieEntity
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.libraries.arch.extensions.isDouble
import com.lcabral.artseventh.libraries.arch.extensions.isZero
import com.lcabral.artseventh.libraries.arch.extensions.orFalse

    fun List<MovieEntity>.toMovies(): List<Movie> {
        return map { it.toMovie() }
    }

    fun MovieEntity.toMovie(): Movie {
        return Movie(
            id = id.isZero(),
            adult = adult.orFalse(),
            backdropPath = backdropPath,
            name = name,
            overview = overview,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            posterPath = posterPath,
            popularity = popularity.isDouble(),
            release = release,
            voteAverage = voteAverage.isDouble(),
            voteCount = voteCount.isZero(),
            video = video.orFalse()
        )
    }

    fun Movie.toMovieEntity(): MovieEntity {
        return MovieEntity(
            id = id.isZero(),
            adult = adult.orFalse(),
            backdropPath = backdropPath,
            name = name,
            overview = overview,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            posterPath = posterPath,
            popularity = popularity.isDouble(),
            release = release,
            voteAverage = voteAverage.isDouble(),
            voteCount = voteCount.isZero(),
            video = video.orFalse()
        )
    }
