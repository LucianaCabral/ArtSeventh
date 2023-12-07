package com.lcabral.artseventh.core.data.hub.data.repository

import androidx.annotation.VisibleForTesting
import com.google.gson.Gson
import com.lcabral.artseventh.core.data.hub.commomTest.TestRuleRemote
import java.io.BufferedReader
import java.net.HttpURLConnection

internal const val MOVIE_SUCCESS_RESPONSE = "success_response.json"

@VisibleForTesting
internal fun TestRuleRemote.successResponse(fileBlock: () -> String) {
    val body = readFromJSONToString(fileBlock())
    mockWebServerResponse(body, HttpURLConnection.HTTP_OK)
}

inline fun <reified DataModel> readFromJSONToModel(jsonFileName: String): DataModel =
    jsonFileName.toBufferedReader().use { reader ->
        Gson().fromJson(reader, DataModel::class.java)
    }

fun readFromJSONToString(jsonFileName: String): String =
    jsonFileName.toBufferedReader().use { reader ->
        reader?.readText().toString()
    }

fun String.toBufferedReader(): BufferedReader? {
    return Thread.currentThread()
        .contextClassLoader?.getResourceAsStream(this)?.bufferedReader()
}
