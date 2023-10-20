package com.lcabral.artseventh.core.data.remote

import retrofit2.Retrofit

class HttpClientImpl(
    private val retrofit: Retrofit
) : HttpClient {
    override fun <T> create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}

