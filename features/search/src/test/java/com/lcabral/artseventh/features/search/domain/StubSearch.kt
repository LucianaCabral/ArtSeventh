package com.lcabral.artseventh.features.search.domain

import com.lcabral.artseventh.features.search.domain.model.Search

internal object StubSearchMovie {
    fun stubSearch(): List<Search> {
        return listOf(
            Search(
                overview = "",
                id= 1,
                posterPath = "jpg",
                name = "null",
                backdropPath = "",
                originalLanguage = "",
                originalTitle = "",
                release = "",
                popularity = 8.1,
                adult = true,
                voteAverage = 7.7,
                voteCount = 6,
                video = true
            ),
            Search(
                overview = "",
                id= 1,
                posterPath = "jpg",
                name = "null",
                backdropPath = "",
                originalLanguage = "",
                originalTitle = "",
                release = "",
                popularity = 8.1,
                adult = true,
                voteAverage = 7.7,
                voteCount = 6,
                video= true
            )
        )
    }
}
