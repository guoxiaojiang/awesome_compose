package com.compose.awsome.news.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.awsome.news.data.DataState
import com.compose.awsome.news.data.NewsResponse
import com.compose.awsome.news.data.VideoListResponse
import com.compose.awsome.news.net.ApiService
import com.compose.awsome.news.ui.theme.NewsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    var theme by mutableStateOf(NewsTheme.Theme.Light)
    var currentPage by mutableStateOf(0)
}