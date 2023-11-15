package com.lcabral.artseventh.features.movies.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetMovieUseCase
import com.lcabral.artseventh.core.domain.model.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.features.movies.R
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

internal class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val saveFavoriteUseCase: SaveFavoriteMovieUseCase,
    private val getFavoritesUseCase:GetFavoritesMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,

    ) : ViewModel() {

    private val _viewState: MutableLiveData<MovieStateView> = MutableLiveData<MovieStateView>()
    val viewState: LiveData<MovieStateView> = _viewState

    private val _viewAction: MutableLiveData<MovieViewAction> = MutableLiveData<MovieViewAction>()
    val viewAction: LiveData<MovieViewAction> = _viewAction

    init {
        getMovies()
        getFavoriteMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            movieUseCase.invoke()
                .flowOn(dispatcher)
                .onStart { handleLoading() }
                .catch { handleError() }
                .collect(::handleMoviesSuccess)
        }
    }


    private fun handleLoading() {
        MovieStateView(flipperChild = LOADING_CHILD, getMoviesResultItems = null)
    }

    private fun handleError() {
        _viewState.value = MovieStateView(flipperChild = FAILURE_CHILD)
        _viewAction.value = MovieViewAction.ShowError
    }

    private fun handleMoviesSuccess(movieResults: List<Movie>) {
//        Log.d("<L>", "MovieSuccess:${movieResults} ")
        _viewState.value = MovieStateView(
            flipperChild = SUCCESS_CHILD,
            getMoviesResultItems = movieResults
        )
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            getFavoritesUseCase()
        }
    }

    private fun saveFavorite(movie: Movie) {
        viewModelScope.launch {
           runCatching {
               saveFavoriteUseCase(movie)
           }.onFailure { onSaveFavoriteFailure(it)}
        }
    }
    private fun onSaveFavoriteFailure(error: Throwable) {
        if (error is Error) {
            _viewState.value = MovieStateView(flipperChild = FAILURE_CHILD,
                message = R.string.error_message )
        }
        Timber.e(error.message, error.toString())
    }

    fun onAdapterItemClicked(id: Int, movie: Movie) {
        when (id) {
            R.id.add_favorite_checkbox -> {
                saveFavorite(movie)
            }
            R.id.movie_image -> {
                _viewAction.value = MovieViewAction.GoToDetails(movie)
            }
        }
    }
}
