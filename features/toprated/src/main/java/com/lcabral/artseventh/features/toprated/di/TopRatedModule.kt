package com.lcabral.artseventh.features.toprated.di

import com.lcabral.artseventh.core.common.navigation.TopRatedNavigation
import com.lcabral.artseventh.core.domain.model.usecase.GetTopRatedUseCase
import com.lcabral.artseventh.features.toprated.navigation.TopRatedNavigationImpl
import com.lcabral.artseventh.features.toprated.presentation.viewmodel.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object TopRatedModule {
    val modules get() = listOf(presentationModules, additionalModules)

    private val presentationModules: Module = module {

        viewModel {
            TopRatedViewModel(GetTopRatedUseCase(repository = get()))
        }
    }

    private val additionalModules: Module = module {
        factory<TopRatedNavigation> { TopRatedNavigationImpl() }
    }
}
