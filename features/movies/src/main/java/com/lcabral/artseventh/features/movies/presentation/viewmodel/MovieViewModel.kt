package com.lcabral.artseventh.features.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.GetMovieUseCase
import com.lcabral.artseventh.core.domain.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.features.movies.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

private const val ITEMS_PER_PAGE = 20


internal class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val saveFavoriteUseCase: SaveFavoriteMovieUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getFavoritesUseCase: GetFavoritesMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,

    ) : ViewModel() {

    private val _viewState: MutableStateFlow<MovieStateView> = MutableStateFlow(MovieStateView())
    val viewState: StateFlow<MovieStateView> = _viewState

//    private val _viewState: MutableLiveData<MovieStateView> = MutableLiveData<MovieStateView>()
//    val viewState: LiveData<MovieStateView> = _viewState

    private val _viewAction: MutableLiveData<MovieViewAction> = MutableLiveData<MovieViewAction>()
    val viewAction: LiveData<MovieViewAction> = _viewAction

    init {
        getMovies()
        getFavoriteMovies()
    }

    private fun getMovies() {
        val movies = movieUseCase()
            .onStart { handleLoading() }
            .catch { handleError() }
            .cachedIn(viewModelScope)
        _viewState.value = MovieStateView(movies = movies)

    }


    private fun handleLoading() {
        MovieStateView(flipperChild = LOADING_CHILD, getMoviesResultItems = null)
    }

    private fun handleError() {
        _viewState.value = MovieStateView(flipperChild = FAILURE_CHILD)
        _viewAction.value = MovieViewAction.ShowError
    }

//    private fun handleMoviesSuccess(movies: Flow<PagingData<Movie>>) {
//        _viewState.value = MovieStateView(
//            flipperChild = SUCCESS_CHILD,
//            movies = movies
//        )
//    }

    private fun getFavoriteMovies() {
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
            _viewState.value = MovieStateView(
                flipperChild = FAILURE_CHILD,
                message = R.string.delete_error_message
            )
        }
        Timber.e(error.message, error.toString())
    }

    private fun onSaveFavoriteFailure(error: Throwable) {
        if (error is Error) {
            _viewState.value = MovieStateView(
                flipperChild = FAILURE_CHILD,
                message = R.string.error_message
            )
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
                _viewAction.value = MovieViewAction.GoToDetails(movie)
            }
        }
    }
}
