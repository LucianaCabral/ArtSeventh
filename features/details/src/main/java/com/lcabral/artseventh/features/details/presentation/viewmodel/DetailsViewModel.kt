package com.lcabral.artseventh.features.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.usecase.SetFavoriteUseCase
import kotlinx.coroutines.launch

internal class DetailsViewModel(
    private val addMovieUseCase: SetFavoriteUseCase
) : ViewModel() {

    private val _viewAction: MutableLiveData<ViewAction> = MutableLiveData<ViewAction>()
    private val _viewState: MutableLiveData<ViewState> = MutableLiveData<ViewState>()

    val viewAction: LiveData<ViewAction> = _viewAction
    val viewState: LiveData<ViewState> = _viewState


    fun onBackPressed() {
        _viewAction.value = ViewAction.NavigateBack
    }

    private fun insertMovie() = viewModelScope.launch {
        _viewAction.value = ViewAction.FavoriteClicked

    }

    fun onFavoriteClicked() {
        _viewAction.value = ViewAction.FavoriteClicked
    }

    fun onSaveTOFavorite(isSuccess: Boolean) {
        val message = if (isSuccess) {
            " "
        } else " "
        _viewAction.value = ViewAction.SaveToFavorite(movie = 11)
    }
}

