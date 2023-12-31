package com.lcabral.artseventh.features.popular.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.GetPopularUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class PopularViewModel(
    private val popularUseCase: GetPopularUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState: MutableLiveData<PopularViewState> = MutableLiveData<PopularViewState>()
    val viewState: LiveData<PopularViewState> = _viewState

    private val _viewAction: MutableLiveData<PopularViewAction> =
        MutableLiveData<PopularViewAction>()
    val viewAction: LiveData<PopularViewAction> = _viewAction

    init {
        getPopular()
    }

    private fun getPopular() {
        viewModelScope.launch {
            popularUseCase.invoke()
                .flowOn(dispatcher)
                .onStart { handleLoading() }
                .catch { handleError(it) }
                .collect(::handlePopularSuccess)
        }
    }

    private fun handlePopularSuccess(popularResults: List<Movie>) {
        _viewState.value = PopularViewState(
            flipperChild = SUCCESS_CHILD, getPopularResultItems = popularResults
        )
    }

    private fun handleError(cause: Throwable) {
        if (cause is Error) {
            _viewState.value =
                PopularViewState(flipperChild = FAILURE_CHILD)
        }
    }

    private fun handleLoading() {
        _viewState.value =
            PopularViewState(flipperChild = LOADING_CHILD)
    }

    fun onAdapterItemClicked(popular: Movie) {
        _viewAction.value = PopularViewAction.GoToDetails(popular)
    }
}
