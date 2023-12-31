package com.lcabral.artseventh.libraries.arch.test.utils

import androidx.paging.PagingData
import com.lcabral.artseventh.core.domain.model.Movie
object MovieStub {
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

    val pagingData = PagingData.from(getMovies())

    fun getMovie(): Movie {
        return (
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
}
