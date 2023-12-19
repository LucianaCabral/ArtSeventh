package com.lcabral.artseventh.core.data.remote.service

import com.lcabral.artseventh.core.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/now_playing")
    suspend fun getMovie(
        @Query("page") page: Int
    ): MovieResponse

    @GET("/3/movie/popular")
    suspend fun getPopular(): MovieResponse

    @GET("/3/movie/top_rated")
    suspend fun getTopRated(): MovieResponse

    @GET("/3/trending/movie/week")
    suspend fun getTrendings(): MovieResponse

    @GET("/3/movie/upcoming")
    suspend fun getUpcoming(): MovieResponse

    @GET("/3/movie/now_playing")
    suspend fun getDetails(): MovieResponse
}
