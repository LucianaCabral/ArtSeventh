package com.lcabral.artseventh.features.trendings.di

import com.lcabral.artseventh.core.common.navigation.TrendingNavigation
import com.lcabral.artseventh.core.domain.usecase.GetTrendingUseCase
import com.lcabral.artseventh.features.trendings.navigation.TrendingNavigationImpl
import com.lcabral.artseventh.features.trendings.presentation.presentation.viewmodel.TrendingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object TrendingModule {
    val modules get() = listOf(presentationModules, additionalModules)

    private val presentationModules : Module = module {

        viewModel {
            TrendingViewModel(GetTrendingUseCase(repository = get()))
        }
    }

    private val additionalModules:  Module = module {
        factory<TrendingNavigation> { TrendingNavigationImpl() }
    }
}
