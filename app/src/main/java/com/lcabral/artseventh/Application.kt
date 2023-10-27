package com.lcabral.artseventh

import android.app.Application
import com.lcabral.artseventh.core.data.hub.di.HubModule
import com.lcabral.artseventh.core.data.local.di.LocalModule
import com.lcabral.artseventh.core.data.remote.di.RemoteModule
import com.lcabral.artseventh.di.MainModule.modules
import com.lcabral.artseventh.features.details.di.DetailsModule
import com.lcabral.artseventh.features.movies.di.MoviesModule
import com.lcabral.artseventh.features.popular.di.PopularModule
import com.lcabral.artseventh.features.search.di.SearchDetailsModule
import com.lcabral.artseventh.features.search.di.SearchModule
import com.lcabral.artseventh.features.toprated.di.TopRatedModule
import com.lcabral.artseventh.features.trendings.di.TrendingModule
import com.lcabral.artseventh.features.upcoming.di.UpcomingModule
import com.lcabral.artseventh.featuresdashboard.di.DashboardModule
import com.lcabral.artseventh.header.di.HeaderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
        }
        modules.load()
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }

    private fun List<Module>.load() {
        loadKoinModules(
            modules = this +
                    DashboardModule.modules +
                    HeaderModule.modules +
                    RemoteModule.modules +
                    LocalModule.modules +
                    MoviesModule.modules +
                    HubModule.modules +
                    SearchModule.modules +
                    SearchDetailsModule.modules +
                    PopularModule.modules +
                    TrendingModule.modules +
                    UpcomingModule.modules +
                    TopRatedModule.modules +
                    DetailsModule.modules
        )
    }
}
