package com.simform.news.network

import com.simform.news.data.model.News
import com.simform.news.data.model.SourceResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines?country=in")
    fun getNews(
        @Query(value = "category") category: String,
        @Query(value = "apiKey") apiKey: String,
        @Query(value = "page") page: Int = 1,
        @Query(value = "pageSize") pageSize: Int = 50,
    ): Call<News>

    @GET("top-headlines/{sources}")
    suspend fun getSources(
        @Path(value = "sources") sources: String,
        @Query(value = "apiKey") apiKey: String
    ): Response<SourceResponse>

}