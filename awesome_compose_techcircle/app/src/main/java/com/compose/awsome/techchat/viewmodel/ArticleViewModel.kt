package com.compose.awsome.techchat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.compose.awsome.techchat.model.article.ArticleResponse
import com.compose.awsome.techchat.net.ApiService

class ArticleViewModel : BaseViewModel() {

    var currentTag by mutableStateOf(2)

    val articlesLiveData = MutableLiveData<ArticleResponse>()
    fun fetchArticleList() {
        launch {
            val articlesModel = ApiService.getArticles()
            articlesLiveData.value = articlesModel
        }
    }

}