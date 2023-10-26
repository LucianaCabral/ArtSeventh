package com.lcabral.artseventh.featuresdashboard.di

import com.lcabral.artseventh.core.common.navigation.DashboardNavigation
import com.lcabral.artseventh.featuresdashboard.navigation.DashboardNavigationImpl
import org.koin.core.module.Module
import org.koin.dsl.module

object DashboardModule {
    val modules get() = listOf(additionalModules)

    private val additionalModules: Module = module {
        factory<DashboardNavigation> { DashboardNavigationImpl() }
    }
}
