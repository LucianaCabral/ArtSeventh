package com.lcabral.artseventh.features.movies.presentation.viewmodel

import androidx.paging.PagingData
import com.lcabral.artseventh.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal const val LOADING_CHILD = 0
internal const val SUCCESS_CHILD = 1
internal const val FAILURE_CHILD = 2

internal data class MovieStateView(
    val movies: Flow<PagingData<Movie>> = emptyFlow(),
    val flipperChild: Int = LOADING_CHILD,
    val shouldShowError: Boolean = false,
    val errorMessageRes: String? = null,
    val shouldShowEmptyLayout: Boolean = false,
) {

//    fun getFailureState(errorMessageRes: Int) = copy(
//        flipperChild = LOADING_CHILD,
//        shouldShowError = true,
//        errorMessageRes = errorMessageRes,
//        shouldShowEmptyLayout = movies.isNull()
//    )
}
