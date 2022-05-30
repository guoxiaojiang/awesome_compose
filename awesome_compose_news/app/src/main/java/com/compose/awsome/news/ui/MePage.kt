package com.compose.awsome.news.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.awsome.news.ui.theme.NewsTheme

@Composable
fun MineTopBar() {
  NewsTopBar(title = "我的")
}

@Composable
fun MinePge() {
  Column(Modifier.fillMaxSize()) {
    MineTopBar()
    Box(
      Modifier
        .background(NewsTheme.colors.background)
        .fillMaxSize()
    ) {
      Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "我的页面")
        val content: MutableState<String?> = remember { mutableStateOf(null) }

        Button(onClick = { content.value = "我的收藏、我的评价、我的好友、答谢记录、我的地址...." }) {
        }
        Text(content.value ?: "暂无内容")
      }
    }
  }
}

@Composable
fun Foo() {
  var text by remember { mutableStateOf("") }
  Log.d("TAG", "Foo")
  Button(onClick = {
    text = "$text $text"
  }.also { Log.d("TAG", "Button") }) {
    Log.d("TAG", "Button content lambda")
    Text(text).also { Log.d("TAG", "Text") }
  }
}


@Preview
@Composable
fun Counter() {

  var count by remember {
    mutableStateOf(40)
  }

  Button(onClick = { count++ }, modifier = Modifier.padding(count.dp)) {
    Text("I've been clicked $count times")
  }
}