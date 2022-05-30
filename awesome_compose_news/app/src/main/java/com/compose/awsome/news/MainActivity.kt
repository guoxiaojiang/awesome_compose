package com.compose.awsome.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.compose.awsome.news.ui.Home
import com.compose.awsome.news.ui.theme.NewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val viewModel: NewsViewModel by viewModels()
        setContent {
            NewsTheme(theme = viewModel.theme) {
                Home()
            }
        }
    }
}