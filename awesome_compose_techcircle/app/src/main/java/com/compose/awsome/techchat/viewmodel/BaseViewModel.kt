package com.compose.awsome.techchat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.awsome.techchat.model.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val awesomeTechStateLiveData = MutableLiveData<DataState>().apply {
        value = DataState.Loading
    }

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            kotlin.runCatching {
                block()
            }.onSuccess {
                awesomeTechStateLiveData.value = DataState.Success
            }.onFailure {
                awesomeTechStateLiveData.value = DataState.Error(it.message)
            }
        }
    }

}