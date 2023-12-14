package com.lcabral.artseventh.features.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.core.domain.model.Movie
import com.lcabral.artseventh.core.domain.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.IsFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.SaveFavoriteMovieUseCase
import kotlinx.coroutines.launch

internal class DetailsViewModel(
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
    private val getFavoritesMoviesUseCase: GetFavoritesMoviesUseCase,
    private val isFavoritesMoviesUseCase: IsFavoritesMoviesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val args: MovieArgs?,
) : ViewModel() {

    private val _viewAction: MutableLiveData<DetailViewAction> = MutableLiveData<DetailViewAction>()
    private val _viewState: MutableLiveData<DetailViewState> = MutableLiveData<DetailViewState>(
        DetailViewState()
    )

    val viewAction: LiveData<DetailViewAction> = _viewAction
    val viewState: LiveData<DetailViewState> = _viewState

    init {
        movieIsFavorite()
    }

    fun onBackPressed() {
        _viewAction.value = DetailViewAction.NavigateBack
    }

    fun onFavorite(movie: Movie, isFavorite: Boolean) {
        viewModelScope.launch {
            runCatching {
                if (isFavorite) {
                    saveFavoriteMovieUseCase(movie = movie)
                } else deleteFavoriteUseCase(movie = movie)
            }
        }
    }

    private fun movieIsFavorite() {
        viewModelScope.launch {
            runCatching {
              isFavoritesMoviesUseCase(args?.id ?: 0)
            }.onSuccess {
                _viewState.value = _viewState.value?.copy(isFavoriteChecked = it)
            }
        }
    }

    fun onGetFavorite() {
        viewModelScope.launch {
            runCatching {
                getFavoritesMoviesUseCase()
            }
        }
    }
}

