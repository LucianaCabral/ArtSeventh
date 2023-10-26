package com.lcabral.artseventh.features.search.di

import com.lcabral.artseventh.features.search.presentation.viewmodel.SearchDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object SearchDetailsModule {
    val modules get() = listOf(presentationModules)

    private val presentationModules: Module = module {
        viewModel {
            SearchDetailsViewModel()
        }
    }
}
