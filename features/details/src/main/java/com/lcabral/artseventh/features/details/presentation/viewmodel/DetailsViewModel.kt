package com.lcabral.artseventh.features.details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.lcabral.artseventh.core.domain.model.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetFavoriteMoviesUseCase
import com.lcabral.artseventh.core.domain.model.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetDetailsUseCase

private const val ARGS_MOVIE = "argsMovie"

internal class DetailsViewModel(

    private val getDetailsUseCase: GetDetailsUseCase,
    private val getFavoritesUseCase: GetFavoriteMoviesUseCase,
    private val addFavoriteMovieUseCase: SaveFavoriteMovieUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,

    ) : ViewModel() {

    private val _viewAction: MutableLiveData<DetailViewAction> = MutableLiveData<DetailViewAction>()
    private val _viewState: MutableLiveData<DetailViewState> = MutableLiveData<DetailViewState>()

    val viewAction: LiveData<DetailViewAction> = _viewAction
    val viewState: LiveData<DetailViewState> = _viewState


//    init {
//        getDetails()
//    }
    fun onBackPressed() {
        _viewAction.value = DetailViewAction.NavigateBack
    }

    private fun getMovieDetailFailure(message: String?) {
        _viewState.value = DetailViewState(errorMessage = message)
    }

//    fun onGetFavoritesMovies() =
//        viewModelScope.launch {
//            runCatching {
//            }
//        }

//    fun onAddFavoriteMovie(movie:Movie) =
//        viewModelScope.launch {
//            movie?.let { addFavoriteMovieUseCase.invoke(it) }
//            println("movie = $movie")
//        }
//        viewModelScope.launch {
//            println("<Lo> ${addFavoriteMovieUseCase(movie = movie)}")
//            addFavoriteMovieUseCase(movie = movie)
//        }

}

