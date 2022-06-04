package com.compose.awsome.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.awsome.news.data.DataState
import com.compose.awsome.news.data.NewsResponse
import com.compose.awsome.news.data.Order
import com.compose.awsome.news.net.ApiService
import com.compose.awsome.news.ui.theme.NewsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

  val stateLiveData = MutableLiveData<DataState>().apply {
    value = DataState.Loading
  }

  private fun launch(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch {
      kotlin.runCatching {
        block()
      }.onSuccess {
        stateLiveData.value = DataState.Success
      }.onFailure {
        stateLiveData.value = DataState.Error(it.message)
      }
    }
  }

  val newsLiveData = MutableLiveData<NewsResponse>()

  fun getNewsLists() {
    launch {
      val newsModel = ApiService.getNews()
      newsLiveData.value = newsModel
    }
  }

  var orderList by mutableStateOf(
    listOf(
      Order("金枪鱼紫菜包饭", "￥18", R.mipmap.header),
      Order("便宜坊",  "￥98", R.mipmap.header),
      Order("凉皮先生.凉皮.肉夹馍.酸辣粉.各种各样.面食等等省略号", "￥22", R.mipmap.header),
      Order("小恒水饺（望京SOHO店）", "￥17", R.mipmap.header),
    )
  )
  var theme by mutableStateOf(NewsTheme.Theme.Light)
}