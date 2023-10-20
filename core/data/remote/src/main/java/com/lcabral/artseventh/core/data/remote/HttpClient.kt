package com.lcabral.artseventh.core.data.remote

interface HttpClient {
    fun <T> create(clazz: Class<T>): T
}
