package com.lcabral.artseventh.features.favorites.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetFavoritesMoviesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class FavoriteViewModel(
    private val getFavoritesUseCase: GetFavoritesMoviesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private val movie: Movie? = null

    private val _viewState: MutableLiveData<FavoriteViewState> =
        MutableLiveData<FavoriteViewState>()
    val viewState: LiveData<FavoriteViewState> = _viewState

    private val _viewAction: MutableLiveData<FavoriteViewAction> =
        MutableLiveData<FavoriteViewAction>()
    val viewAction: LiveData<FavoriteViewAction> = _viewAction

    init {
        getMovies()

    }

    private fun getMovies() {
        getFavoritesUseCase.invoke()
            .onStart { _viewState.value = FavoriteViewState(isFavoriteChecked = true) }
            .onCompletion { _viewState.value = FavoriteViewState(isLoading = false) }
            .onEach { movies ->
                _viewState.value = FavoriteViewState(getFavoritesMovies = movies)
                Log.d("<L>", "getMoviesFromFavorite:${movies} ")
            }
            .catch { onGetMovieFailure(it) }
            .launchIn(viewModelScope)

    }

    private fun onGetMovieFailure(throwable: Throwable) {
        _viewState.value = FavoriteViewState(errorMessage = throwable.message)
    }

    fun deleteFavorite(movie: Movie) {
        viewModelScope.launch {
            deleteFavoriteUseCase(movie)
        }
    }

    fun onAdapterItemClicked(movie: Movie) {
        _viewAction.value = FavoriteViewAction.FavoriteClicked(movie)
    }
}