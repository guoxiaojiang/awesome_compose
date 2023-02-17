package com.compose.awsome.techchat.model.video

class VideoListResponse(
    val data: VideoList,
    val code: Int,
    val msg: String
)

class VideoList(
    val videos: MutableList<VideoItem> = mutableListOf()
)

class VideoItem(
    val title: String,
    val pic: String,
    val author: String,
    val avatar: String,
    val videoUrl: String
)