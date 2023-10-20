package com.lcabral.artseventh.core.data.remote.di

import com.lcabral.artseventh.core.data.remote.BuildConfig
import com.lcabral.artseventh.core.data.remote.HttpClientImpl
import com.lcabral.artseventh.core.data.remote.RetrofitClient
import com.lcabral.artseventh.core.data.remote.interceptor.AuthInterceptor
import com.lcabral.artseventh.core.data.remote.HttpClient
import com.lcabral.artseventh.core.data.remote.interceptor.HeaderInterceptor
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

object RemoteModule {
    val modules
        get() = listOf(dataModule)

    private val dataModule = module {
        single<HttpClient> {
            HttpClientImpl(
                retrofit = getRetrofitClient().create()
            )
        }
    }

    private fun getRetrofitClient(): RetrofitClient {
        return RetrofitClient(
            interceptors = listOf(
                AuthInterceptor(apiKey = BuildConfig.API_KEY),
                HeaderInterceptor(),
                httpLoggingInterceptor()
            )
        )
    }

    private fun httpLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE
        }
}