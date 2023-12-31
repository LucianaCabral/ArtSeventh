package com.lcabral.artseventh.features.popular.presentation

import androidx.annotation.VisibleForTesting
import com.lcabral.artseventh.core.data.remote.model.MovieResponse
import com.lcabral.artseventh.libraries.arch.test.utils.readFromJSONToModel
import com.lcabral.artseventh.libraries.arch.test.utils.readFromJSONToString

private const val SUCCESS_RESPONSE = "success_response.json"
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
object StubResponse {

    val movieResponse = readFromJSONToModel<MovieResponse>(SUCCESS_RESPONSE)
    val successBodyResponse = readFromJSONToString(SUCCESS_RESPONSE)
}
