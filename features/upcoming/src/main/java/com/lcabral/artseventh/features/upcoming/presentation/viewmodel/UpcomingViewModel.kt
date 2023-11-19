package com.lcabral.artseventh.features.upcoming.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.GetUpcomingUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class UpcomingViewModel(
    private val upcomingUseCase: GetUpcomingUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _viewState: MutableLiveData<UpcomingViewState> =
        MutableLiveData<UpcomingViewState>()
    val viewState: LiveData<UpcomingViewState> = _viewState

    private val _viewAction: MutableLiveData<UpcomingViewAction> =
        MutableLiveData<UpcomingViewAction>()
    val viewAction: LiveData<UpcomingViewAction> = _viewAction

    fun getUpcoming() {
        viewModelScope.launch {
            upcomingUseCase()
                .flowOn(dispatcher)
                .onStart { handleLoading() }
                .catch { handleError() }
                .collect(::handleTrendingsSuccess)
        }
    }

    private fun handleTrendingsSuccess(popularResults: List<Movie>) {
        _viewState.value = UpcomingViewState(
            flipperChild = SUCCESS_CHILD,
            getUpcomingResultItems = popularResults
        )
    }

    private fun handleError() {
        _viewState.value = UpcomingViewState(flipperChild = FAILURE_CHILD)
    }

    private fun handleLoading() {
        _viewState.value = UpcomingViewState(flipperChild = LOADING_CHILD, getUpcomingResultItems = null)
    }

    fun onAdapterItemClicked(movie: Movie) {
        _viewAction.value = UpcomingViewAction.GoToDetails(movie)

    }
}
