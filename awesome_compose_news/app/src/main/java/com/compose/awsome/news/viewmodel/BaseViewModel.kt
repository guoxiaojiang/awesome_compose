package com.compose.awsome.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.awsome.news.data.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

  val newsStateLiveData = MutableLiveData<DataState>().apply {
    value = DataState.Loading
  }

  fun launch(block: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch {
      kotlin.runCatching {
        block()
      }.onSuccess {
        newsStateLiveData.value = DataState.Success
      }.onFailure {
        newsStateLiveData.value = DataState.Error(it.message)
      }
    }
  }

}