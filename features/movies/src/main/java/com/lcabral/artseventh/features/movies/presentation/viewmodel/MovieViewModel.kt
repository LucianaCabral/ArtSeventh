package com.lcabral.artseventh.features.movies.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.model.usecase.GetMovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,

    ) : ViewModel() {

    private val _viewState: MutableLiveData<MovieStateView> = MutableLiveData<MovieStateView>()
    val viewState: LiveData<MovieStateView> = _viewState

    private val _viewAction: MutableLiveData<MovieViewAction> = MutableLiveData<MovieViewAction>()
    val viewAction: LiveData<MovieViewAction> = _viewAction

    init {
        getMovies()
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
        Log.d("<L>", "MovieSuccess:${movieResults} ")
        _viewState.value = MovieStateView(
            flipperChild = SUCCESS_CHILD,
            getMoviesResultItems = movieResults
        )
    }

    fun onAdapterItemClicked(movie: Movie) {
        _viewAction.value = MovieViewAction.GoToDetails(movie)
    }
}
