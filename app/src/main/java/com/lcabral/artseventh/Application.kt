package com.lcabral.artseventh

import android.app.Application
import com.lcabral.artseventh.core.data.remote.di.RemoteModule
import com.lcabral.artseventh.di.MainModule.modules
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
                    RemoteModule.modules
        )
    }
}
