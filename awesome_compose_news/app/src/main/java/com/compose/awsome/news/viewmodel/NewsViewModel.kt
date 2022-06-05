package com.compose.awsome.news.viewmodel

import androidx.lifecycle.MutableLiveData
import com.compose.awsome.news.data.NewsResponse
import com.compose.awsome.news.net.ApiService

class NewsViewModel : BaseViewModel() {

    val newsLiveData = MutableLiveData<NewsResponse>()
    fun fetchNewsList() {
        launch {
            val newsModel = ApiService.getNews()
            newsLiveData.value = newsModel
        }
    }

}