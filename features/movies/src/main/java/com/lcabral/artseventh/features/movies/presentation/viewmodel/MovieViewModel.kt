package com.lcabral.artseventh.features.movies.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.core.domain.model.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetFavoriteMoviesUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetMovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val saveFavoriteUseCase: SaveFavoriteMovieUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getFavoriteUseCase: GetFavoriteMoviesUseCase,
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
            getFavoriteUseCase()
        }
    }

    fun onFavoriteClicked(movie: Movie) {
        viewModelScope.launch {
            movie.isFavorite = movie.isFavorite
            if (movie.isFavorite) {
                saveFavoriteUseCase(movie)
                _viewAction.value = MovieViewAction.SaveFavorite(movie)
                Log.d("<L>", "onFavoriteClickedFromMovie:$movie ")
            } else {
                deleteFavoriteUseCase(movie)
            }
        }
    }

    fun onAdapterItemClicked(movie: Movie) {
        _viewAction.value = MovieViewAction.GoToDetails(movie)
    }
}
