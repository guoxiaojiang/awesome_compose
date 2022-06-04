package com.compose.awsome.news.net.api

import com.compose.awsome.news.data.NewsResponse
import retrofit2.http.GET

interface Api {

    companion object {
        const val NEWS_URL = "awesome_compose_news/homepage.json"
    }

    @GET(NEWS_URL)
    suspend fun getNews(): NewsResponse
}