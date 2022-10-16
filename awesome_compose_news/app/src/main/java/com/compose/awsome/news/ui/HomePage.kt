package com.compose.awsome.news.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.compose.awsome.news.R
import com.compose.awsome.news.viewmodel.NewsViewModel
import com.compose.awsome.news.ui.theme.NewsTheme
import com.compose.awsome.news.viewmodel.AppViewModel
import com.compose.awsome.news.viewmodel.VideosViewModel

@Composable
fun HomeBottomBar(current: Int, currentChanged: (Int) -> Unit) {
  NewsBottomBar {
    HomeBottomItem(
      Modifier
        .weight(1f)
        .clickable { currentChanged(0) },
      if (current == 0) R.mipmap.home_page_selected else R.mipmap.home_page_normal,
      "首页",
      if (current == 0) NewsTheme.colors.iconCurrent else NewsTheme.colors.icon
    )
    HomeBottomItem(
      Modifier
        .weight(1f)
        .clickable { currentChanged(1) },
      if (current == 1) R.mipmap.icon_video_selected else R.mipmap.icon_video_normal,
      "视频",
      if (current == 1) NewsTheme.colors.iconCurrent else NewsTheme.colors.icon
    )
    HomeBottomItem(
      Modifier
        .weight(1f)
        .clickable { currentChanged(2) },
      if (current == 2) R.mipmap.icon_square_selected else R.mipmap.icon_square_normal,
      "广场",
      if (current == 2) NewsTheme.colors.iconCurrent else NewsTheme.colors.icon
    )
    HomeBottomItem(
      Modifier
        .weight(1f)
        .clickable { currentChanged(3) },
      if (current == 3) R.mipmap.icon_mine_selected else R.mipmap.icon_mine_normal,
      "我的",
      if (current == 3) NewsTheme.colors.iconCurrent else NewsTheme.colors.icon
    )
  }
}

@Composable
fun HomeBottomItem(
  modifier: Modifier = Modifier,
  @DrawableRes iconId: Int,
  title: String,
  tint: Color
) {
  Column(
    modifier.padding(0.dp, 8.dp, 0.dp, 8.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(painterResource(iconId), null, Modifier.size(24.dp))
    Text(title, fontSize = 11.sp, color = tint)
  }
}

@Preview(showBackground = true)
@Composable
fun NewsBottomPreview() {
  HomeBottomBar(0) {}
}

@Composable
fun Home(navHostController: NavHostController) {
  val appViewModel: AppViewModel = viewModel()
  val newsViewModel: NewsViewModel = viewModel()
  val videosViewModel: VideosViewModel = viewModel()
  NewsTheme(theme = appViewModel.theme) {
    Box {
      Column(Modifier.fillMaxSize()) {
        val pagerState: PagerState = run {
          remember(appViewModel.theme) { PagerState(maxPage = 3) }
        }
        Pager(pagerState, Modifier.weight(1f)) {
          when (page) {
            0 -> {
              NewsList(newsViewModel)
            }
            1 -> {
              VideoList(videosViewModel)
            }
            2 -> {
              SquarePage()
            }
            3 -> {
              MinePge(newsViewModel, navHostController)
            }
          }
        }
        pagerState.currentPage = appViewModel.currentPage
        HomeBottomBar(pagerState.currentPage) {
          appViewModel.currentPage = it
          pagerState.currentPage = it
        }
      }
    }
  }
}