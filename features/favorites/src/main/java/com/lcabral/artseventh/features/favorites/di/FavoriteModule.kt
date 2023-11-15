package com.lcabral.artseventh.features.favorites.di

import com.lcabral.artseventh.core.common.navigation.FavoritesNavigation
import com.lcabral.artseventh.core.domain.model.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.model.usecase.IsFavoritesMoviesUseCase
import com.lcabral.artseventh.core.domain.model.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.features.favorites.navigation.FavoritesNavigationImpl
import com.lcabral.artseventh.features.favorites.presentation.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object FavoriteModule {
    val modules get() = listOf(presentationModule, additionalModules)

    private val presentationModule: Module = module {

        viewModel {
            FavoriteViewModel(
                getFavoritesUseCase = GetFavoritesMoviesUseCase(repository = get()),
                DeleteFavoriteUseCase(repository = get()),
            )
        }
    }

    private val additionalModules: Module = module {
        factory<FavoritesNavigation> { FavoritesNavigationImpl() }
    }
}
