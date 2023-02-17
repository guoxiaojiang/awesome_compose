package com.compose.awsome.techchat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.awsome.techchat.ui.theme.NewsTheme

class AppViewModel : ViewModel() {
    var theme by mutableStateOf(NewsTheme.Theme.Light)
    var currentPage by mutableStateOf(0)
}