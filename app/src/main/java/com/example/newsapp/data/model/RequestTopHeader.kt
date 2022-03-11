package com.example.newsapp.data.model

data class RequestTopHeader(val articles: List<News?> = emptyList(), val totalResults: Int? = null)