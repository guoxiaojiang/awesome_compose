package com.compose.awsome.news.data

class NewsResponse(
    val data: NewsList,
    val code: Int,
    val msg: String
)

class NewsList(
    val news: MutableList<NewsItem> = mutableListOf()
)

class NewsItem(
    val title: String,
    val pic: String,
    val date: String,
    val author: String,
    val content: String
)