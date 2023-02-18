package com.compose.awsome.techchat.ui

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
import com.compose.awsome.techchat.R
import com.compose.awsome.techchat.ui.article.ArticleList
import com.compose.awsome.techchat.viewmodel.ArticleViewModel
import com.compose.awsome.techchat.ui.theme.AwesomeTechTheme
import com.compose.awsome.techchat.ui.video.VideoList
import com.compose.awsome.techchat.viewmodel.AppViewModel
import com.compose.awsome.techchat.viewmodel.VideosViewModel

@Composable
fun HomeBottomBar(current: Int, currentChanged: (Int) -> Unit) {
    AwesomeTechBottomBar {
        HomeBottomItem(
          Modifier
            .weight(1f)
            .clickable { currentChanged(0) },
            if (current == 0) R.mipmap.home_page_selected else R.mipmap.home_page_normal,
            "文章",
            if (current == 0) AwesomeTechTheme.colors.iconCurrent else AwesomeTechTheme.colors.icon
        )
        HomeBottomItem(
          Modifier
            .weight(1f)
            .clickable { currentChanged(1) },
            if (current == 1) R.mipmap.icon_video_selected else R.mipmap.icon_video_normal,
            "视频",
            if (current == 1) AwesomeTechTheme.colors.iconCurrent else AwesomeTechTheme.colors.icon
        )
        HomeBottomItem(
          Modifier
            .weight(1f)
            .clickable { currentChanged(2) },
            if (current == 2) R.mipmap.icon_circle_selected else R.mipmap.icon_circle_normal,
            "人脉",
            if (current == 2) AwesomeTechTheme.colors.iconCurrent else AwesomeTechTheme.colors.icon
        )
        HomeBottomItem(
          Modifier
            .weight(1f)
            .clickable { currentChanged(3) },
            if (current == 3) R.mipmap.icon_question_selected else R.mipmap.icon_question_normal,
            "问答",
            if (current == 3) AwesomeTechTheme.colors.iconCurrent else AwesomeTechTheme.colors.icon
        )
        HomeBottomItem(
          Modifier
            .weight(1f)
            .clickable { currentChanged(4) },
            if (current == 4) R.mipmap.icon_mine_selected else R.mipmap.icon_mine_normal,
            "我的",
            if (current == 4) AwesomeTechTheme.colors.iconCurrent else AwesomeTechTheme.colors.icon
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBottomPreview() {
    HomeBottomBar(0) {}
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

@Composable
fun Home(navHostController: NavHostController, onBack: () -> Unit) {
    val appViewModel: AppViewModel = viewModel()
    val articleViewModel: ArticleViewModel = viewModel()
    val videosViewModel: VideosViewModel = viewModel()
    AwesomeTechTheme(theme = appViewModel.theme) {
        Box {
            Column(Modifier.fillMaxSize()) {
                val pagerState: PagerState = run {
                    remember(appViewModel.theme) { PagerState(maxPage = 4) }
                }
                Pager(pagerState, Modifier.weight(1f)) {
                    when (page) {
                        0 -> {
                            ArticleList(articleViewModel, navHostController)
                        }
                        1 -> {
                            VideoList(videosViewModel)
                        }
                        2 -> {
                            FriendCirclePge(articleViewModel, navHostController)
                        }
                        3 -> {
                            QuestionPage(onBack)
                        }
                        4 -> {
                            MinePge(articleViewModel, navHostController)
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