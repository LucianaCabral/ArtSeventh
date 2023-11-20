package com.lcabral.artseventh.features.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class DetailsViewModel : ViewModel() {

    private val _viewAction: MutableLiveData<DetailViewAction> = MutableLiveData<DetailViewAction>()
    private val _viewState: MutableLiveData<DetailViewState> = MutableLiveData<DetailViewState>()

    val viewAction: LiveData<DetailViewAction> = _viewAction
    val viewState: LiveData<DetailViewState> = _viewState

    fun onBackPressed() {
        _viewAction.value = DetailViewAction.NavigateBack
    }
}

