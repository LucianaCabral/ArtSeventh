package com.lcabral.artseventh.features.movies.di

import com.lcabral.artseventh.core.common.navigation.MovieNavigation
import com.lcabral.artseventh.core.domain.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.GetMovieUseCase
import com.lcabral.artseventh.core.domain.usecase.IsFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.features.movies.navigation.MovieNavigationImpl
import com.lcabral.artseventh.features.movies.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object MoviesModule {
    val modules get() = listOf(presentationModule, additionalModules, domainModule)

    private val domainModule: Module = module {

        factory { IsFavoritesMoviesUseCase(repository = get()) }
    }

    private val presentationModule: Module = module {

        viewModel {
            MovieViewModel(
                movieUseCase = GetMovieUseCase(repository = get()),
                saveFavoriteUseCase = SaveFavoriteMovieUseCase(repository = get()),
                getFavoritesUseCase = GetFavoritesMoviesUseCase(repository = get()),
                deleteFavoriteUseCase = DeleteFavoriteUseCase(repository = get())
            )
        }
    }

    private val additionalModules: Module = module {
        factory<MovieNavigation> { MovieNavigationImpl() }
    }
}
