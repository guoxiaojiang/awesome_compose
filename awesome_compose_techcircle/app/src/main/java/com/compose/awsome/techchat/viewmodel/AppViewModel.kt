package com.compose.awsome.techchat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.awsome.techchat.ui.theme.AwesomeTechTheme

class AppViewModel : ViewModel() {
    var theme by mutableStateOf(AwesomeTechTheme.Theme.Light)
    var currentPage by mutableStateOf(0)
}