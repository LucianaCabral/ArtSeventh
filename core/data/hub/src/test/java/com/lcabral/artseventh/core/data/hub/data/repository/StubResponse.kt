package com.lcabral.artseventh.core.data.hub.data.repository

import androidx.annotation.VisibleForTesting
import com.lcabral.artseventh.core.data.remote.model.MovieResponse

private const val SUCCESS_RESPONSE = "success_response.json"
private const val FAILURE_RESPONSE = "failure_response.json"

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
object StubResponse {

    val movieResponse = readFromJSONToModel<MovieResponse>(SUCCESS_RESPONSE)

    val successBodyResponse = readFromJSONToString(SUCCESS_RESPONSE)

    val failureBodyResponse = readFromJSONToString(FAILURE_RESPONSE)
}
