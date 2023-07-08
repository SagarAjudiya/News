package com.simform.news.repository

import com.simform.news.data.model.News
import com.simform.news.network.ApiClient
import com.simform.news.util.URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadlineRepository {

    fun getNews(category: String, callback: (News) -> Unit) {
        val response = ApiClient.apiInterface.getNews(category, URL.apiKey)
        response.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback(it) }
                } else {

                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {

            }
        })
    }

}