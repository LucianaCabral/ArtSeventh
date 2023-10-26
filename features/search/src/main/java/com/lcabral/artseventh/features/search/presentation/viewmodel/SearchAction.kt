package com.lcabral.artseventh.features.search.presentation.viewmodel

import com.lcabral.artseventh.features.search.domain.model.Search

internal sealed class SearchAction {
    object ShowError : SearchAction()
    data class GotoDetails(val search: Search) : SearchAction()
}
