package com.compose.awsome.news.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.compose.awsome.news.viewmodel.NewsViewModel
import com.compose.awsome.news.data.NewsItem
import com.compose.awsome.news.ui.theme.NewsTheme

@Composable
fun NewsListTopBar() {
    NewsTopBar(title = "Compose 头条")
}

@Preview(showBackground = true)
@Composable
fun NewsListTopBarPreview() {
    NewsListTopBar()
}

@Composable
fun NewsListItem(
    item: NewsItem
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = {
            })
    ) {
        Image(
           painter =  rememberImagePainter(data = item.pic),
            contentDescription = "pic", modifier = Modifier
                .padding(12.dp, 4.dp, 8.dp, 4.dp)
                .size(88.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                item.title,
                fontSize = 18.sp,
                color = NewsTheme.colors.textPrimary,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                item.content,
                fontSize = 15.sp,
                color = NewsTheme.colors.textSecondary,
                maxLines = 6,
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
                    color = NewsTheme.colors.textPrimaryMe,
                )

                Text(
                    item.date,
                    fontSize = 13.sp,
                    color = NewsTheme.colors.textPrimaryMe,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }

        }
    }
}

@Composable
fun NewsList(newsItem: List<NewsItem>) {
    LazyColumn(
        Modifier
            .background(NewsTheme.colors.listItem)
            .fillMaxWidth()
    ) {
        itemsIndexed(newsItem) { index, item ->
            NewsListItem(item)
            if (index < newsItem.size - 1) {
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
fun NewsList(viewModel: NewsViewModel) {

    val state by viewModel.newsStateLiveData.observeAsState()
    val newsModel by viewModel.newsLiveData.observeAsState()

    LoadingPage(state = state!!,
        loadInit = {
            viewModel.fetchNewsList()
        }, contentView = {
            Column(Modifier.fillMaxSize()) {
                NewsListTopBar()
                Box(
                    Modifier
                        .background(NewsTheme.colors.background)
                        .fillMaxSize()
                ) {
                    newsModel?.data?.let { NewsList(it.news) }
                }
            }
        })
}