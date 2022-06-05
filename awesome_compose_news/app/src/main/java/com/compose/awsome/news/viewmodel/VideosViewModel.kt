package com.compose.awsome.news.viewmodel

import androidx.lifecycle.MutableLiveData
import com.compose.awsome.news.data.VideoListResponse
import com.compose.awsome.news.net.ApiService

class VideosViewModel : BaseViewModel() {
    val videoLiveData = MutableLiveData<VideoListResponse>()
    fun fetchVideoList() {
        launch {
            val videoList = ApiService.getVideoList()
            videoLiveData.value = videoList
        }
    }
}