package com.lcabral.artseventh.features.upcoming.di

import com.lcabral.artseventh.core.common.navigation.UpcomingNavigation
import com.lcabral.artseventh.core.domain.model.usecase.GetUpcomingUseCase
import com.lcabral.artseventh.features.upcoming.navigation.UpcomingNavigationImpl
import com.lcabral.artseventh.features.upcoming.presentation.viewmodel.UpcomingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object UpcomingModule {
    val modules get() = listOf(presentationModules, additionalModules)

    private val presentationModules : Module = module {

        viewModel {
            UpcomingViewModel(GetUpcomingUseCase(repository = get()))
        }
    }

    private val additionalModules: Module = module {
        factory<UpcomingNavigation> { UpcomingNavigationImpl() }
    }
}
