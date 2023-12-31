package com.lcabral.artseventh.features.movies.presentation.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.GetMovieUseCase
import com.lcabral.artseventh.core.domain.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.features.movies.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.ServiceConfigurationError

internal class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val saveFavoriteUseCase: SaveFavoriteMovieUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesMoviesUseCase

) : ViewModel() {

    private val _state: MutableStateFlow<MovieStateView> = MutableStateFlow(MovieStateView())
    val state: StateFlow<MovieStateView> = _state.asStateFlow()

    private val _action: Channel<MovieViewAction> = Channel<MovieViewAction>(Channel.CONFLATED)
    val action: Flow<MovieViewAction> = _action.receiveAsFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        val movies = movieUseCase()
            .catch { handleError(it) }
            .cachedIn(viewModelScope)
        _state.value = _state.value.copy(movies = movies, flipperChild = SUCCESS_CHILD)
    }

    private fun handleError(throwable: Throwable) {
        if (throwable is Error) {
            _state.value = _state.value.copy(
                flipperChild = FAILURE_CHILD,
                shouldShowError = true, errorMessageRes = throwable.message
            )
        }
    }

    fun getFavoriteMovies() {
        viewModelScope.launch {
            getFavoritesUseCase()
        }
    }

    private fun saveFavorite(movie: Movie) {
        viewModelScope.launch {
            runCatching {
                saveFavoriteUseCase(movie)
            }
                .onFailure { onSaveFavoriteFailure(it) }
        }
    }

    private fun deleteFavorite(movie: Movie) {
        viewModelScope.launch {
            runCatching {
                deleteFavoriteUseCase(movie)
            }.onFailure {
                onDeleteFavoriteSuccess(it)
            }
        }
    }

    private fun onDeleteFavoriteSuccess(error: Throwable) {
        if (error is Error) {
            _state.value =
                _state.value.copy(flipperChild = FAILURE_CHILD)
        }
        Timber.e(error.message, error.toString())
    }

    private fun onSaveFavoriteFailure(error: Throwable) {
        if (error is Error) {
            _state.value =
                _state.value.copy(flipperChild = FAILURE_CHILD)
        }
        Timber.e(error.message, error.toString())
    }

    fun onAdapterItemClicked(id: Int, movie: Movie, isFavorite: Boolean) {
        when (id) {
            R.id.add_favorite_checkbox -> {
                if (isFavorite) {
                    saveFavorite(movie)
                } else {
                    deleteFavorite(movie)
                }
            }

            R.id.movie_image -> {
                _action.trySend(MovieViewAction.GoToDetails(movie))
            }
        }
    }
}
