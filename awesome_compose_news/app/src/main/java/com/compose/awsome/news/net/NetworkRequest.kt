package com.compose.awsome.news.net

import com.compose.awsome.news.net.api.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val COMPOSE_BOOK_URL = "https://guoxj.oss-cn-beijing.aliyuncs.com/compose/"

private val okHttpClient by lazy {
    OkHttpClient.Builder().build()
}

private val retrofit by lazy {
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(
            COMPOSE_BOOK_URL
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

object ApiService : Api by retrofit.create(
    Api::
    class.java
)
