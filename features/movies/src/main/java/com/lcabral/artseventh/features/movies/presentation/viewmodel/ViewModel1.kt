package com.lcabral.artseventh.features.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class ViewModel1<S, A>(initialState: S) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _action = Channel<A>(Channel.CONFLATED)
    val action: Flow<A> = _action.receiveAsFlow()

    protected fun onState(stateBlock: (S) -> S) {
        _state.update { state -> stateBlock(state) }
    }
}
