package com.lcabral.artseventh.features.movies.presentation.viewmodel

import com.lcabral.artseventh.core.domain.model.Movie

fun movieDataTest(): Movie {
    return Movie(
        id = 1,
        name = "Avatar: The Way of Water",
        overview = "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
        posterPath = "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
        release = "2022-12-14",
        voteAverage = 7.65,
        backdropPath = "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
        voteCount = 9977,
        adult = false,
        originalTitle = "Avatar: The Way of Water",
        originalLanguage = "en",
        popularity = 9.9,
        video = false
    )
}

fun movieData(id: Int = 1) = Movie(
    id = 1,
    name = "Avatar: The Way of Water",
    overview = "Set more than a decade after the events of the first film, " +
            "learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
    posterPath = "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
    release = "2022-12-14",
    voteAverage = 7.65,
    backdropPath = "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
    voteCount = 9977,
    adult = false,
    originalTitle = "Avatar: The Way of Water",
    originalLanguage = "en",
    popularity = 9.9,
    video = false
)

fun moviesData(): List<Movie> {
    return listOf(
        movieData(id = 1),
        movieData(id = 2),
        movieData(id = 3)
    )
}
