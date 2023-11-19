package com.lcabral.artseventh.features.toprated.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.GetTopRatedUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class TopRatedViewModel(
    private val topRatedUseCase: GetTopRatedUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState: MutableLiveData<TopRatedState> = MutableLiveData<TopRatedState>()
    val viewState: LiveData<TopRatedState> = _viewState

    private val _viewAction: MutableLiveData<TopRatedAction> = MutableLiveData<TopRatedAction>()
    val viewAction: LiveData<TopRatedAction> = _viewAction

    fun getTopRated() {
        viewModelScope.launch {
            topRatedUseCase.invoke()
                .flowOn(dispatcher)
                .onStart { handleLoading() }
                .catch { handleError() }
                .collect(::handleTopRatedSuccess)
        }
    }

    private fun handleTopRatedSuccess(topRatedResults: List<Movie>) {
        _viewState.value = TopRatedState(
            flipperChild = SUCCESS_CHILD,
            getTopRatedResultItems = topRatedResults
        )
    }

    private fun handleError() {
        _viewState.value = TopRatedState(flipperChild = FAILURE_CHILD)
    }

    private fun handleLoading() {
        _viewState.value =
            TopRatedState(flipperChild = LOADING_CHILD, getTopRatedResultItems = null)
    }

    fun onAdapterItemClicked(topRated: Movie) {
        _viewAction.value = TopRatedAction.GoToDetails(topRated)
    }
}
