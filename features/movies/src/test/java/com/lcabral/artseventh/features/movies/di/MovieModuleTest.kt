package com.lcabral.artseventh.features.movies.di

import com.lcabral.artseventh.core.domain.repository.MovieRepository
import com.lcabral.artseventh.libraries.arch.test.utils.MainDispatcherMainTestRule
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class MovieModuleTest: KoinTest {
    @get:Rule
    val dispatcherRule = MainDispatcherMainTestRule()

    private val networkModules: Module = module {
        factory<MovieRepository> {
            mockk(relaxed = true) }
    }

    @Test
    fun `checkModules Should Koin provides dependencies When invoke MoviesModule`() {
        startKoin {
            modules(MoviesModule.modules + networkModules)
        }.checkModules()
    }
}