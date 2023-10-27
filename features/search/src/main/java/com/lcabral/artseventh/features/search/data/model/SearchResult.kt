package com.lcabral.artseventh.features.search.data.model

import com.google.gson.annotations.SerializedName

internal data class SearchResult(
    @SerializedName("results")
    val results: List<SearchResponse>
)
