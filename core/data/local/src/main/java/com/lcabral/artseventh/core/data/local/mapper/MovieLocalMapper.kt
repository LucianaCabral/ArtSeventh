package com.lcabral.artseventh.core.data.local.mapper

import com.lcabral.artseventh.core.data.local.model.MovieEntity
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.libraries.arch.extensions.isDouble
import com.lcabral.artseventh.libraries.arch.extensions.isZero
import com.lcabral.artseventh.libraries.arch.extensions.orFalse
    fun fromEntityToDomain(movieEntity: MovieEntity): Movie {
        return with(movieEntity) {
            Movie(
                id = id.isZero(),
                adult = adult.orFalse(),
                backdropPath = backdropPath.orEmpty(),
                name = name.orEmpty(),
                overview = overview.orEmpty(),
                originalLanguage = originalLanguage.orEmpty(),
                originalTitle = originalTitle.orEmpty(),
                posterPath = posterPath.orEmpty(),
                popularity = popularity.isDouble(),
                release = release.orEmpty(),
                voteAverage = voteAverage.isDouble(),
                voteCount = voteCount.isZero(),
                video = video.orFalse(),
            )
        }
    }

    fun Movie.fromDomainToEntity(): MovieEntity {
           return MovieEntity(
                id = id,
                adult = adult,
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
    }

    fun List<MovieEntity>.toMovies(): List<Movie> {
        return map { it.toMovie() }
    }

    fun MovieEntity.toMovie(): Movie {
        return Movie(
            id = id.isZero(),
            adult = adult.orFalse(),
            backdropPath = backdropPath.orEmpty(),
            name = name.orEmpty(),
            overview = overview.orEmpty(),
            originalLanguage = originalLanguage.orEmpty(),
            originalTitle = originalTitle.orEmpty(),
            posterPath = posterPath.orEmpty(),
            popularity = popularity.isDouble(),
            release = release.orEmpty(),
            voteAverage = voteAverage.isDouble(),
            voteCount = voteCount.isZero(),
            video = video.orFalse()

        )
    }

    fun Movie.toMovieEntity(): MovieEntity {
        return MovieEntity(
            id = id.isZero(),
            adult = adult.orFalse(),
            backdropPath = backdropPath.orEmpty(),
            name = name.orEmpty(),
            overview = overview.orEmpty(),
            originalLanguage = originalLanguage.orEmpty(),
            originalTitle = originalTitle.orEmpty(),
            posterPath = posterPath.orEmpty(),
            popularity = popularity.isDouble(),
            release = release.orEmpty(),
            voteAverage = voteAverage.isDouble(),
            voteCount = voteCount.isZero(),
            video = video.orFalse()
        )
    }
