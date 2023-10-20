package com.lcabral.artseventh.core.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal const val READ_TIMEOUT = 30L
internal const val WRITE_TIMEOUT = 30L
internal const val CONNECT_TIMEOUT = 30L

class RetrofitClient(
    private val baseUrl: String = BuildConfig.API_URL,
    private val interceptors: List<Interceptor>,
) {

    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOKHttpClient(interceptors))
            .build()

    }

    private fun createOKHttpClient(
        interceptors: List<Interceptor>
    ): OkHttpClient {

        val builder = OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        }

        return interceptors
            .forEach { builder.addInterceptor(it) }
            .run { builder.build() }
    }
}
