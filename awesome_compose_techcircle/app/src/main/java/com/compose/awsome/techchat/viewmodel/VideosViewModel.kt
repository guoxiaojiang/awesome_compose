package com.compose.awsome.techchat.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.compose.awsome.techchat.model.video.VideoListResponse
import com.compose.awsome.techchat.net.ApiService

class VideosViewModel : BaseViewModel() {
    val playListIndex = mutableStateOf(0)
    val videoLiveData = MutableLiveData<VideoListResponse>()
    fun fetchVideoList() {
        launch {
            val videoList = ApiService.getVideoList()
            videoLiveData.value = videoList
        }
    }
}