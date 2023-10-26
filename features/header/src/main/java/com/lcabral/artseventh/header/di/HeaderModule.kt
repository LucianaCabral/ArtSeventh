package com.lcabral.artseventh.header.di

import com.lcabral.artseventh.core.common.navigation.HeaderNavigation
import com.lcabral.artseventh.header.navigation.HeaderNavigationImpl
import org.koin.core.module.Module
import org.koin.dsl.module

object HeaderModule {
    val modules get() = listOf(additionalModules)
}

private val additionalModules: Module = module {
    factory<HeaderNavigation> { HeaderNavigationImpl() }
}
