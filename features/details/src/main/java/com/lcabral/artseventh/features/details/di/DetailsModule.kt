package com.lcabral.artseventh.features.details.di

import com.lcabral.artseventh.core.common.navigation.DetailsNavigation
import com.lcabral.artseventh.core.common.navigation.MovieArgs
import com.lcabral.artseventh.core.domain.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.IsFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.features.details.navigation.DetailsNavigationImpl
import com.lcabral.artseventh.features.details.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object DetailsModule {
    val modules get() = listOf(additionalModule, presentationModules)

    private val presentationModules: Module = module {

        viewModel {
                (args: MovieArgs?) ->
            DetailsViewModel(
                saveFavoriteMovieUseCase = SaveFavoriteMovieUseCase(repository = get()),
                getFavoritesMoviesUseCase = GetFavoritesMoviesUseCase(repository = get()),
                isFavoritesMoviesUseCase = IsFavoritesMoviesUseCase(repository = get()),
                deleteFavoriteUseCase = DeleteFavoriteUseCase(repository = get()),
                args = args
            )
        }
    }

    private val additionalModule: Module = module {
        factory<DetailsNavigation> { DetailsNavigationImpl() }
    }
}
