package com.lcabral.artseventh.features.search.presentation.viewmodel

internal sealed class SearchDetailsAction {
    object GoToMoreDetails : SearchDetailsAction()
    object NavigateBack : SearchDetailsAction()
}
