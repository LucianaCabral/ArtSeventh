package com.lcabral.artseventh.core.data.remote.service

import com.lcabral.artseventh.core.data.remote.model.MovieResult
import retrofit2.http.GET

interface MovieService {
    @GET("/3/movie/now_playing")
    suspend fun getMovie(): MovieResult

    @GET("/3/movie/popular")
    suspend fun getPopular(): MovieResult

    @GET("/3/movie/top_rated")
    suspend fun getTopRated(): MovieResult

    @GET("/3/trending/movie/week")
    suspend fun getTrendings(): MovieResult

    @GET("/3/movie/upcoming")
    suspend fun getUpcoming(): MovieResult
}
