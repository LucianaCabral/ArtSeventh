package com.lcabral.artseventh.core.data.remote.model

import com.google.gson.annotations.SerializedName
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.libraries.arch.extensions.isDouble
import com.lcabral.artseventh.libraries.arch.extensions.isZero
import com.lcabral.artseventh.libraries.arch.extensions.orFalse

data class ResultResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("title")
    val name: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
    ) {

    fun toMovie() = Movie(
        id = id.isZero(),
        name = name.orEmpty(),
        posterPath = posterPath.orEmpty(),
        backdropPath = backdropPath.orEmpty(),
        release = releaseDate.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        popularity = popularity.isDouble(),
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount.isZero(),
        adult = adult.orFalse(),
        overview = overview.orEmpty(),
        video = video.orFalse()
    )
}

fun MovieResponse.toMovie() = results.map {
    it.toMovie()
}

fun List<ResultResponse>.toMovies(): List<Movie> {
    return this.map {
        it.toMovie()
    }
}
