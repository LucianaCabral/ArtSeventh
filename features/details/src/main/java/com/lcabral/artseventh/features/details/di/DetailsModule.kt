package com.lcabral.artseventh.features.details.di

import com.lcabral.artseventh.core.common.navigation.DetailsNavigation
import com.lcabral.artseventh.core.domain.model.usecase.DeleteFavoriteUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetFavoriteMoviesUseCase
import com.lcabral.artseventh.core.domain.model.usecase.SaveFavoriteMovieUseCase
import com.lcabral.artseventh.core.domain.model.usecase.GetDetailsUseCase
import com.lcabral.artseventh.features.details.navigation.DetailsNavigationImpl
import com.lcabral.artseventh.features.details.presentation.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object DetailsModule {
    val modules get() = listOf(additionalModule, presentationModules)

    private val presentationModules: Module = module {

        viewModel {
//                (args: MovieArgs) ->
            DetailsViewModel(
                addFavoriteMovieUseCase = SaveFavoriteMovieUseCase(repository = get()),
                getFavoritesUseCase = GetFavoriteMoviesUseCase(repository = get()),
                deleteFavoriteUseCase = DeleteFavoriteUseCase(repository = get()),
                getDetailsUseCase = GetDetailsUseCase(repository = get())
//                args = args
//                savedStateHandle = SavedStateHandle(get())
            )
        }
    }

    private val additionalModule: Module = module {
        factory<DetailsNavigation> { DetailsNavigationImpl() }
    }
}
