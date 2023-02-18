package com.compose.awsome.techchat.net.api

import com.compose.awsome.techchat.model.article.ArticleDetailResponse
import com.compose.awsome.techchat.model.article.ArticleResponse
import com.compose.awsome.techchat.model.video.VideoListResponse
import retrofit2.http.GET

interface Api {

    companion object {
        const val HOMEPAGE_URL = "homepage.json"
        const val VIDEOS_URL = "video_list.json"
        const val ARTICLE_DETAIL = "article_detail.json"
    }

    @GET(HOMEPAGE_URL)
    suspend fun getArticles(): ArticleResponse

    @GET(VIDEOS_URL)
    suspend fun getVideoList(): VideoListResponse

    @GET(ARTICLE_DETAIL)
    suspend fun getArticleDetail(): ArticleDetailResponse
}