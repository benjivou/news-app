package com.example.newsapp.data.model

/**
 * Represent the response on the TopHeadealines path of the NewsApi
 */
data class ResponseTopHeadealines(
    val articles: List<News?> = emptyList(),
    val totalResults: Int? = null
)