package com.compose.awsome.news.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.awsome.news.R
import com.compose.awsome.news.NewsViewModel
import com.compose.awsome.news.ui.theme.NewsTheme
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun NewsTopBar(title: String, onBack: (() -> Unit)? = null) {
  Box(
    Modifier
      .background(NewsTheme.colors.background)
      .fillMaxWidth()
      .statusBarsPadding()
  ) {
    Row(
      Modifier
        .height(48.dp)
    ) {
      Spacer(Modifier.weight(1f))
      val viewModel: NewsViewModel = viewModel()
      Icon(
        painterResource(R.drawable.ic_change_theme),
        "切换主题",
        Modifier
          .clickable(onClick = {
            viewModel.theme = when (viewModel.theme) {
              NewsTheme.Theme.Light -> NewsTheme.Theme.Dark
              NewsTheme.Theme.Dark -> NewsTheme.Theme.Light
            }
          })
          .align(Alignment.CenterVertically)
          .size(36.dp)
          .padding(8.dp),
        tint = NewsTheme.colors.icon
      )
    }
    Text(title, Modifier.align(Alignment.Center), color = NewsTheme.colors.textPrimary)
  }
}

@Composable
fun NewsBottomBar(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
  Row(
    modifier
      .fillMaxWidth()
      .background(NewsTheme.colors.bottomBar)
      .padding(4.dp, 0.dp)
      .navigationBarsPadding(),
    content = content
  )
}