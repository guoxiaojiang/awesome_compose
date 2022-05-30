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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.awsome.news.NewsViewModel
import com.compose.awsome.news.data.Poi
import com.compose.awsome.news.ui.theme.NewsTheme

@Composable
fun PoiListTopBar() {
    NewsTopBar(title = "震惊头条")
}

@Preview(showBackground = true)
@Composable
fun PoiListTopBarPreview() {
    PoiListTopBar()
}

@Composable
fun PoiListItem(
    poi: Poi
) {
    Row(
      Modifier
        .fillMaxWidth()
        .clickable(onClick = {
//        poi.name = "name...."
        })
    ) {
        Image(
            painterResource(poi.pic), "pic", Modifier
            .padding(12.dp, 4.dp, 8.dp, 4.dp)
            .size(88.dp)
            .clip(RoundedCornerShape(6.dp))
        )
        Column(
          Modifier
            .weight(1f)
            .align(Alignment.CenterVertically)
        ) {
            Text(
                poi.name,
                fontSize = 18.sp,
                color = NewsTheme.colors.textPrimary,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            Text(poi.desc, fontSize = 14.sp, color = NewsTheme.colors.textSecondary)
            Text(
                poi.price,
                fontSize = 15.sp,
                color = NewsTheme.colors.price,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun NewsList(pois: List<Poi>) {
    LazyColumn(
      Modifier
        .background(NewsTheme.colors.listItem)
        .fillMaxWidth()
    ) {
        itemsIndexed(pois) { index, poi ->
            PoiListItem(poi)
            if (index < pois.size - 1) {
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
    Column(Modifier.fillMaxSize()) {
        PoiListTopBar()
        Box(
          Modifier
            .background(NewsTheme.colors.background)
            .fillMaxSize()
        ) {
            NewsList(viewModel.poiList)
        }
    }
}