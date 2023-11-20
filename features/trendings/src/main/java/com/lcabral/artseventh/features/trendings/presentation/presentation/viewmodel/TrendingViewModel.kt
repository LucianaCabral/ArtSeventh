package com.lcabral.artseventh.features.trendings.presentation.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.GetTrendingUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class TrendingViewModel(
    private val trendingUseCase: GetTrendingUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _viewState: MutableLiveData<ViewState> = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    private val _viewAction: MutableLiveData<ViewAction> = MutableLiveData<ViewAction>()
    val viewAction: LiveData<ViewAction> = _viewAction

    fun getTrendings() {
        viewModelScope.launch {
            trendingUseCase.invoke()
                .flowOn(dispatcher)
                .onStart { handleLoading() }
                .catch { handleError() }
                .collect(::handleTrendingsSuccess)
        }
    }

    private fun handleTrendingsSuccess(trendingResults: List<Movie>) {
        _viewState.value = ViewState(
            flipperChild = SUCCESS_CHILD,
            getTrendingsResultItems = trendingResults
        )
    }

    private fun handleError() {
        _viewState.value = ViewState(flipperChild = FAILURE_CHILD)
    }

    private fun handleLoading() {
        _viewState.value = ViewState(flipperChild = LOADING_CHILD, getTrendingsResultItems = null)
    }

    fun onAdapterItemClicked(movie: Movie) {
        _viewAction.value = ViewAction.GoToDetails(movie)

    }
}

