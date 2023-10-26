package com.lcabral.artseventh.features.search.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.features.search.domain.model.Search
import com.lcabral.artseventh.features.search.domain.usecase.GetSearchUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val searchUseCase: GetSearchUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _viewState: MutableLiveData<SearchViewState> = MutableLiveData<SearchViewState>()
    val viewState: LiveData<SearchViewState> = _viewState

    private val _viewAction: MutableLiveData<SearchAction> = MutableLiveData<SearchAction>()
    val viewAction: LiveData<SearchAction> = _viewAction

    fun submittedSearch(query: String) {
        viewModelScope.launch {
            searchUseCase.invoke(query)
                .flowOn(dispatcher)
                .onStart { handleSearchLoading() }
                .catch { handleError() }
                .collect(::handleSuccess)
        }
    }

    private fun handleSearchLoading() {
        SearchViewState(isLoading = true, searchResultItems = null)
    }

    private fun handleError() {
        _viewState.value = SearchViewState(isLoading = false)
        _viewAction.value = SearchAction.ShowError
    }

    private fun handleSuccess(movies: List<Search>) {
        _viewState.value = SearchViewState(
            isLoading = false,
            searchResultItems = movies
        )
    }

    fun onAdapterItemClicked(search: Search) {
        _viewAction.value = SearchAction.GotoDetails(search)
    }


}
