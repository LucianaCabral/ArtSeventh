package com.lcabral.artseventh.features.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class SearchDetailsViewModel: ViewModel() {

    private val _viewAction: MutableLiveData<SearchDetailsAction> = MutableLiveData<SearchDetailsAction>()
    val viewAction: LiveData<SearchDetailsAction> = _viewAction

    fun onMoreDetailsClicked() {
        _viewAction.value = SearchDetailsAction.GoToMoreDetails
    }

    fun onBackButtonPressed() {
        _viewAction.value = SearchDetailsAction.NavigateBack
    }
}

