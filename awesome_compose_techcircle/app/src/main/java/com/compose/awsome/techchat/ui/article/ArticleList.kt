package com.compose.awsome.techchat.ui.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.compose.awsome.techchat.R
import com.compose.awsome.techchat.viewmodel.ArticleViewModel
import com.compose.awsome.techchat.model.article.ArticleItem
import com.compose.awsome.techchat.route.RouteConfig
import com.compose.awsome.techchat.ui.LoadingPage
import com.compose.awsome.techchat.ui.theme.NewsTheme
import com.compose.awsome.techchat.viewmodel.AppViewModel
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun ArticleListItem(
    item: ArticleItem, navHostController: NavHostController
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                navHostController.navigate(RouteConfig.ROUTE_PAGE_ARTICLE_DETAIL)
            })
    ) {
        Image(
            painter = rememberImagePainter(data = item.pic),
            contentDescription = "pic", modifier = Modifier
                .padding(8.dp, 4.dp, 8.dp, 4.dp)
                .size(88.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp)
        ) {
            Text(
                item.title,
                fontSize = 16.sp,
                color = NewsTheme.colors.textPrimary,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                item.content,
                fontSize = 15.sp,
                color = NewsTheme.colors.textPrimaryMe,
                maxLines = 3,
                modifier = Modifier.padding(top = 5.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 5.dp)
            ) {
                Text(
                    item.author,
                    fontSize = 13.sp,
                    color = NewsTheme.colors.textSecondary,
                )

                Text(
                    item.date,
                    fontSize = 13.sp,
                    color = NewsTheme.colors.textSecondary,
                    modifier = Modifier.padding(start = 6.dp)
                )

                Spacer(Modifier.weight(1f))

                Image(painterResource(R.mipmap.ic_article_label), null, Modifier.size(15.dp))

                (item.label)?.let {
                    Text(
                        it,
                        fontSize = 13.sp,
                        color = NewsTheme.colors.textSecondary,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }

                Text(
                    "10w+",
                    fontSize = 13.sp,
                    color = NewsTheme.colors.textSecondary,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }

        }
    }
}

@Composable
fun ArticleList(articleItems: List<ArticleItem>, viewModel: ArticleViewModel, navHostController: NavHostController) {
    LazyColumn(
        Modifier
            .background(NewsTheme.colors.listItem)
            .fillMaxWidth()
    ) {
        item {
            var imgUrl = "https://tenfei05.cfp.cn/creative/vcg/800/new/VCG211172214797.jpg"
            when (viewModel.currentTag) {
                1 -> imgUrl = "https://tenfei01.cfp.cn/creative/vcg/800/new/VCG211421204357.jpg"
                2 -> imgUrl = "https://tenfei05.cfp.cn/creative/vcg/800/new/VCG211172214797.jpg"
                3 -> imgUrl = "https://tenfei01.cfp.cn/creative/vcg/800/new/VCG41N1401887553.jpg"
            }
            Image(
                painter = rememberImagePainter(data = imgUrl),
                contentDescription = "pic", modifier = Modifier
                    .padding(8.dp, 4.dp, 8.dp, 4.dp)
                    .height(160.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        var showList = articleItems
        when (viewModel.currentTag) {
            1 -> showList = articleItems.reversed()
            2 -> showList = articleItems
            3 -> showList = articleItems.drop(2)
        }

        itemsIndexed(showList) { index, item ->
            ArticleListItem(item, navHostController)
            if (index < articleItems.size - 1) {
                Divider(
                    startIndent = 8.dp,
                    color = NewsTheme.colors.chatListDivider,
                    thickness = 0.8f.dp
                )
            }
        }
    }
}

@Composable
fun CategoryArea(viewModel: ArticleViewModel) {
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
            val viewModel: AppViewModel = viewModel()
            Icon(
                painterResource(R.mipmap.ic_home_search),
                "搜索",
                Modifier
                    .align(Alignment.CenterVertically)
                    .size(36.dp)
                    .padding(8.dp),
                tint = NewsTheme.colors.icon
            )
            Spacer(Modifier.weight(1f))
            Icon(
                painterResource(R.mipmap.ic_home_more),
                "更多",
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
        Row(
            Modifier
                .align(Alignment.Center)
                .height(48.dp)
        ) {
            Box(
                Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight(), Alignment.Center
            ) {
                Text(
                    "在看",
                    fontSize = 16.sp,
                    color = NewsTheme.colors.textPrimary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            viewModel.currentTag = 1
                        })

                if (viewModel.currentTag == 1) {
                    Row(
                        Modifier
                            .height(2.dp)
                            .width(14.dp)
                            .align(Alignment.BottomCenter)
                            .background(NewsTheme.colors.line)
                    ) {}
                }
            }

            Box(
                Modifier
                    .padding(start = 22.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight(),
                Alignment.Center
            ) {
                Text(
                    "热门",
                    fontSize = 16.sp,
                    color = NewsTheme.colors.textPrimary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            viewModel.currentTag = 2
                        })

                if (viewModel.currentTag == 2) {
                    Row(
                        Modifier
                            .height(2.dp)
                            .width(14.dp)
                            .align(Alignment.BottomCenter)
                            .background(NewsTheme.colors.line)
                    ) {}
                }
            }


            Box(
                Modifier
                    .padding(start = 22.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight(),
                Alignment.Center
            ) {
                Text(
                    "订阅",
                    fontSize = 16.sp,
                    color = NewsTheme.colors.textPrimary,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            viewModel.currentTag = 3
                        })

                if (viewModel.currentTag == 3) {
                    Row(
                        Modifier
                            .height(2.dp)
                            .width(14.dp)
                            .align(Alignment.BottomCenter)
                            .background(NewsTheme.colors.line)
                    ) {}
                }
            }
        }
    }
}

@Composable
fun ArticleList(viewModel: ArticleViewModel, navHostController: NavHostController) {

    val state by viewModel.awesomeTechStateLiveData.observeAsState()
    val articlesModel by viewModel.articlesLiveData.observeAsState()

    LoadingPage(state = state!!,
        loadInit = {
            viewModel.fetchArticleList()
        }, contentView = {
            Column(Modifier.fillMaxSize()) {
                CategoryArea(viewModel)
                Box(
                    Modifier
                        .background(NewsTheme.colors.background)
                        .fillMaxSize()
                ) {
                    articlesModel?.data?.let { ArticleList(it.articles, viewModel, navHostController) }
                }
            }
        })
}