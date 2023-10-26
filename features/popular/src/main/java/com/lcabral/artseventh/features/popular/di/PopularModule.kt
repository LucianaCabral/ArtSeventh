package com.lcabral.artseventh.features.popular.di

import com.lcabral.artseventh.core.common.navigation.PopularNavigation
import com.lcabral.artseventh.core.domain.model.usecase.GetPopularUseCase
import com.lcabral.artseventh.features.popular.navigation.PopularNavigationImpl
import com.lcabral.artseventh.features.popular.presentation.viewmodel.PopularViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object PopularModule {
    val modules get() = listOf(presentationModules, additionalModules)

    private val presentationModules: Module = module {

        viewModel {
            PopularViewModel(GetPopularUseCase(repository = get()))
        }
    }

    private val additionalModules: Module = module {
        factory<PopularNavigation> { PopularNavigationImpl() }
    }
}

