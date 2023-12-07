package com.lcabral.artseventh.core.domain.usecase

import com.lcabral.artseventh.core.domain.model.Movie

internal object Stub {
    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                id = 1,
                name = "Barbie",
                posterPath = "jpg",
                backdropPath = "jpg",
                overview = "Lorem ipsum",
                originalLanguage = "eng",
                originalTitle = "Barbie",
                popularity = 8.9,
                voteAverage = 9.9,
                voteCount = 9,
                adult = false,
                release = "2022",
                video = true
            )
        )
    }

    fun getMovie(): Movie {
        return Movie(
            id = 1,
            name = "Barbie",
            posterPath = "jpg",
            backdropPath = "jpg",
            overview = "Lorem ipsum",
            originalLanguage = "eng",
            originalTitle = "Barbie",
            popularity = 8.9,
            voteAverage = 9.9,
            voteCount = 9,
            adult = false,
            release = "2022",
            video = true
        )
    }
}
