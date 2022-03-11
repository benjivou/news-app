package com.example.newsapp.data.repository


import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.RequestTopHeader
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("Apikey") apiKey: String,
    ): Response<RequestTopHeader>
}