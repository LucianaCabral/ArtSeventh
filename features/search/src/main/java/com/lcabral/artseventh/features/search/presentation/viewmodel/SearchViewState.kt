package com.lcabral.artseventh.features.search.presentation.viewmodel

import com.lcabral.artseventh.features.search.domain.model.Search

internal data class SearchViewState(
    val isLoading: Boolean = false,
    val searchResultItems: List<Search>? = null,
    val search: Search? = null,
    val goToMovies: Search? = null,
    val showDetailsMovie: Boolean = false
)

