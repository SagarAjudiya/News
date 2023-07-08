package com.simform.news.repository

import com.simform.news.data.model.SourceResponse
import com.simform.news.network.ApiClient
import com.simform.news.util.URL

class SourcesRepository {

    suspend fun getSources(): SourceResponse? {
        val response = ApiClient.apiInterface.getSources("sources", URL.apiKey)
        val result = response.body()
        return result
    }

}
