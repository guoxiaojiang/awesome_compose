package com.compose.awsome.techchat.net.api

import com.compose.awsome.techchat.model.article.ArticleResponse
import com.compose.awsome.techchat.model.video.VideoListResponse
import retrofit2.http.GET

interface Api {

    companion object {
        const val NEWS_URL = "homepage.json"
        const val VIDEOS_URL = "video_list.json"
    }

    @GET(NEWS_URL)
    suspend fun getArticles(): ArticleResponse

    @GET(VIDEOS_URL)
    suspend fun getVideoList(): VideoListResponse
}