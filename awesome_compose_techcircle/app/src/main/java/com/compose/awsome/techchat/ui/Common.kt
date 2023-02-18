package com.compose.awsome.techchat.ui

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
import com.compose.awsome.techchat.ui.theme.AwesomeTechTheme
import com.compose.awsome.techchat.viewmodel.AppViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.compose.awsome.techchat.R

@Composable
fun AwesomeTechTopBar(title: String = "", onBack: (() -> Unit)? = null) {
  Box(
    Modifier
      .background(AwesomeTechTheme.colors.background)
      .fillMaxWidth()
      .statusBarsPadding()
  ) {
    Row(
      Modifier
        .height(48.dp)
    ) {
      val viewModel: AppViewModel = viewModel()
      if (onBack != null) {
        Icon(
          painterResource(R.drawable.ic_back_btn),
          "返回",
          Modifier
            .clickable(onClick = onBack)
            .align(Alignment.CenterVertically)
            .size(36.dp)
            .padding(8.dp),
          tint = AwesomeTechTheme.colors.icon
        )
      }
      Spacer(Modifier.weight(1f))
      Icon(
        painterResource(R.drawable.ic_change_theme),
        "切换主题",
        Modifier
          .clickable(onClick = {
            viewModel.theme = when (viewModel.theme) {
              AwesomeTechTheme.Theme.Light -> AwesomeTechTheme.Theme.Dark
              AwesomeTechTheme.Theme.Dark -> AwesomeTechTheme.Theme.Light
            }
          })
          .align(Alignment.CenterVertically)
          .size(36.dp)
          .padding(8.dp),
        tint = AwesomeTechTheme.colors.icon
      )
    }
    Text(title, Modifier.align(Alignment.Center), color = AwesomeTechTheme.colors.textPrimary)
  }
}

@Composable
fun AwesomeTechBottomBar(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
  Row(
    modifier
      .fillMaxWidth()
      .background(AwesomeTechTheme.colors.bottomBar)
      .padding(4.dp, 0.dp)
      .navigationBarsPadding(),
    content = content
  )
}