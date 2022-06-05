package com.compose.awsome.news.net.api

import com.compose.awsome.news.data.NewsResponse
import retrofit2.http.GET

interface Api {

    companion object {
        const val NEWS_URL = "homepage.json"
    }

    @GET(NEWS_URL)
    suspend fun getNews(): NewsResponse
}