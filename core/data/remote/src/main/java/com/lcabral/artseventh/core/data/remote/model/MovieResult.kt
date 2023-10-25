package com.lcabral.artseventh.core.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class MovieResult(
    @SerializedName("results")
    val results: List<MovieResponse>
)
