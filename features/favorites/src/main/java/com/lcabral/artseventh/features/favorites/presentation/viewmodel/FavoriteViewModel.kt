package com.lcabral.artseventh.features.favorites.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.features.favorites.R
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

internal class FavoriteViewModel(
    private val getFavoritesUseCase: GetFavoritesMoviesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private val _viewState: MutableLiveData<FavoriteViewState> =
        MutableLiveData<FavoriteViewState>()
    val viewState: LiveData<FavoriteViewState> = _viewState

    private val _viewAction: MutableLiveData<FavoriteViewAction> =
        MutableLiveData<FavoriteViewAction>()
    val viewAction: LiveData<FavoriteViewAction> = _viewAction

    init {
        getFavorites()
    }

    private fun getFavorites() {
        getFavoritesUseCase.invoke()
            .onStart { _viewState.value = FavoriteViewState(isFavoriteChecked = true) }
            .onCompletion { _viewState.value = FavoriteViewState(flipperChild = SUCCESS_CHILD) }
            .onEach { movies ->
                _viewState.value = FavoriteViewState(getFavoritesMovies = movies)
            }
            .catch { onGetFavoriteFailure(it) }
            .launchIn(viewModelScope)

    }

    private fun onGetFavoriteFailure(error: Throwable) {
        if (error is Error) {
            _viewState.value = FavoriteViewState(flipperChild = FAILURE_CHILD,
                message = R.string.error_message)
        }
        Timber.e(error.message, error.toString())
    }

    private fun deleteFavorite(movie: Movie) {
        viewModelScope.launch {
            deleteFavoriteUseCase(movie)
        }
    }

    fun onAdapterItemClicked(id: Int, movie: Movie) {
        when (id) {
            R.id.check_favorite -> {
                deleteFavorite(movie = movie)
            }
            R.id.image_favorite -> {
                _viewAction.value = FavoriteViewAction.GoToDetails(movie = movie)
            }
        }
    }
}
