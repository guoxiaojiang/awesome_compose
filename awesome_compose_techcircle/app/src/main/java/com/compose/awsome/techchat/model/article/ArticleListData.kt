package com.compose.awsome.techchat.model.article

class ArticleResponse(
    val data: ArticleList,
    val code: Int,
    val msg: String
)

class ArticleList(
    val articles: MutableList<ArticleItem> = mutableListOf()
)

class ArticleItem(
    val title: String,
    val pic: String,
    val date: String,
    val author: String,
    val label: String?,
    val content: String,
    val id: Long = 200
)