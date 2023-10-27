package com.lcabral.artseventh.features.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class DetailsViewModel: ViewModel() {

    private val _viewAction: MutableLiveData<ViewAction> = MutableLiveData<ViewAction>()
    val viewAction: LiveData<ViewAction> = _viewAction

    fun onBackPressed() {
        _viewAction.value = ViewAction.NavigateBack
    }
}
