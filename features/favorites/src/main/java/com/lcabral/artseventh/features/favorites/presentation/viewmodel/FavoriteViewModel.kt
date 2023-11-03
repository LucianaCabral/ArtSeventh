package com.lcabral.artseventh.features.favorites.presentation.viewmodel

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetFavoritesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class FavoriteViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase

    ) : ViewModel() {

    private val _viewState: MutableLiveData<ViewState> = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState


    private val _viewAction: MutableLiveData<ViewAction> = MutableLiveData<ViewAction>()
    val viewAction: LiveData<ViewAction> = _viewAction

    private fun getMovies() {
        getFavoritesUseCase()
            .onStart {  }
            .onCompletion {  }
            .onEach {  }
            .catch {  }
            .launchIn(viewModelScope)
    }

    fun onFavorite(movie: Movie) {
        viewModelScope.launch {
            runCatching {
                deleteFavoriteUseCase.invoke(movie)
                    .apply {}}
                .onFailure { }
            }
        }

    private fun onBackPressed() {
        _viewAction.value = ViewAction.NavigateBack
        }

    private fun goToDetails() {
        _viewAction.value = ViewAction.GoToDetails
    }

    private fun onAdapterAddToFavorites(movie: Movie) {
        _viewAction.value = ViewAction.AddToFavorites(movie)
    }
}