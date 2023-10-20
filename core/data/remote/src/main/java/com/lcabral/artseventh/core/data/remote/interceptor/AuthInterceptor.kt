package com.lcabral.artseventh.core.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val API_KEY = "api_key"

internal class AuthInterceptor(
    private val apiKey: String
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url
        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}