package com.example.newsapp.usecases.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.News
import com.example.newsapp.data.private.API_KEY
import com.example.newsapp.data.repository.ApiClient
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    var news: MutableLiveData<List<News?>> = MutableLiveData()
    var errors: MutableLiveData<String> = MutableLiveData()


    init {
        reset()
    }

    /**
     * Resend a request to get the list of articles
     */
    fun reset() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.apiService.getTopHeadlines("us", API_KEY)

                if (response.isSuccessful && response.body() != null) {
                    for (article in response.body()!!.articles) {
                        // Preload all images in cache
                        if (article != null) {
                            Picasso.get().load(article.urlToImage)
                        }
                    }
                    news.postValue(response.body()!!.articles)

                } else {
                    errors.postValue(response.errorBody().toString())
                }

            } catch (e: Exception) {
                errors.postValue(e.message)
            }
        }
    }
}