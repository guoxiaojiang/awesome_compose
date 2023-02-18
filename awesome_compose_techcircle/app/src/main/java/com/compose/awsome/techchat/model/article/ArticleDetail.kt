package com.compose.awsome.techchat.model.article

class ArticleDetailResponse(
    val data: ArticleDetail,
    val code: Int,
    val msg: String
)

class ArticleDetail (
    val title: String,
    val avatar: String,
    val author: String,
    val date: String,
    val intro: String,
    val pic: String,
    val content: String,
    val comment: String
)