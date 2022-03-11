package com.example.newsapp.data.repository


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private const val BASE_URL: String = "https://newsapi.org/v2/"

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)
        OkHttpClient.Builder().build()
    }

    private val retrofit: Retrofit by lazy {


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}