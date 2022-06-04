package com.compose.awsome.news.data

sealed class DataState {
    object Loading : DataState()
    object Success : DataState()
    class Error(val errorMsg: String?) : DataState()

    fun isLoading() = this is Loading

    fun isError() = this is Error
}