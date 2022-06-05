package com.compose.awsome.news.net.api

import com.compose.awsome.news.data.NewsResponse
import com.compose.awsome.news.data.VideoListResponse
import retrofit2.http.GET

interface Api {

    companion object {
        const val NEWS_URL = "homepage.json"
        const val VIDEOS_URL = "video_list.json"
    }

    @GET(NEWS_URL)
    suspend fun getNews(): NewsResponse

    @GET(VIDEOS_URL)
    suspend fun getVideoList(): VideoListResponse
}