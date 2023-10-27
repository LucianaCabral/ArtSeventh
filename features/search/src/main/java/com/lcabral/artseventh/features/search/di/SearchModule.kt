package com.lcabral.artseventh.features.search.di

import com.lcabral.artseventh.core.common.navigation.SearchNavigation
import com.lcabral.artseventh.core.data.remote.HttpClient
import com.lcabral.artseventh.features.search.data.repository.SearchRepositoryImpl
import com.lcabral.artseventh.features.search.data.service.SearchService
import com.lcabral.artseventh.features.search.data.source.SearchDataSource
import com.lcabral.artseventh.features.search.data.source.SearchDataSourceImpl
import com.lcabral.artseventh.features.search.domain.repository.SearchRepository
import com.lcabral.artseventh.features.search.domain.usecase.GetSearchUseCase
import com.lcabral.artseventh.features.search.navigation.SearchNavigationImpl
import com.lcabral.artseventh.features.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

object SearchModule {
    val modules get() = listOf(presentationModules, additionalModules)

    private val presentationModules: Module = module {
        viewModel {
            SearchViewModel(searchUseCase = searchUseCase())
        }
    }

    private val additionalModules: Module = module {
        factory<SearchNavigation> { SearchNavigationImpl() }
    }

    private fun Scope.searchUseCase(): GetSearchUseCase {
        return GetSearchUseCase(getRepository())
    }

    private fun Scope.getRepository(): SearchRepository {
        return SearchRepositoryImpl(
            geSearchDataSource()
        )
    }

    private fun Scope.geSearchDataSource(): SearchDataSource {
        return SearchDataSourceImpl(
            service = get<HttpClient>().create(SearchService::class.java),
        )
    }
}
