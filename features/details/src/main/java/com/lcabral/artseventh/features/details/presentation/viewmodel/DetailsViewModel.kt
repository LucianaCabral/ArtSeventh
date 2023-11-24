package com.lcabral.artseventh.features.details.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    ) : ViewModel() {

    private val _viewAction: MutableLiveData<DetailViewAction> = MutableLiveData<DetailViewAction>()
    private val _viewState: MutableLiveData<DetailViewState> = MutableLiveData<DetailViewState>()

    val viewAction: LiveData<DetailViewAction> = _viewAction
    val viewState: LiveData<DetailViewState> = _viewState

    fun onBackPressed() {
        _viewAction.value = DetailViewAction.NavigateBack
    }

    fun onFavorite(movie: Movie) {
        viewModelScope.launch {
            val isFavorite = isFavoritesMoviesUseCase(movie.id)
            runCatching {
                Log.d("<LU>", "onFavorite: ${movie.id} ")
                if (isFavorite) {
                    Log.d("<LU>", "onFavorite: ${movie.id} ")
                    saveFavoriteMovieUseCase(movie = movie)
                    Log.d("<LU>", "onFavorite: ${movie} ")
                } else saveFavoriteMovieUseCase(movie = movie)
            }
        }
    }

//    fun onFavorite() = _viewState.value?.run {
//        viewModelScope.launch {
//            runCatching {
//                val isFavorite = isFavoritesMoviesUseCase(movie.id)
//                Log.d("<LU>", "onFavorite: ${movie.toMovie().id} ")
//                if (isFavorite) {
//                    Log.d("<LU>", "onFavorite: ${movie.toMovie()} ")
//                    saveFavoriteMovieUseCase(movie = movie.toMovie())
//                    Log.d("<LU>", "onFavorite: ${movie.toMovie()} ")
//
//                } else deleteFavoriteUseCase(movie.toMovie())
//            }
//        }
//    }

    fun onGetFavorite() {
        viewModelScope.launch {
            runCatching {
                Log.d("<LU>", "onGetFavorite: ${getFavoritesMoviesUseCase()} ")
                getFavoritesMoviesUseCase()
            }
        }
    }
}

