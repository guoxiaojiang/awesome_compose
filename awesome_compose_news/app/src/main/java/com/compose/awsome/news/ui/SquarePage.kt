package com.compose.awsome.news.ui

import android.webkit.WebSettings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compose.awsome.news.ui.theme.NewsTheme

@Composable
fun SquarePageTopBar() {
  NewsTopBar(title = "广场")
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
  Text(text = "广场")
}

@Composable
fun SquarePage() {
  Column(Modifier.fillMaxSize()) {
    SquarePageTopBar()
    var rememberWebViewProgress by remember { mutableStateOf(-1) }
    Box(
      Modifier
        .background(NewsTheme.colors.background)
        .fillMaxSize()
    ) {
      NewsWebView(
        modifier = Modifier.fillMaxSize(),
        url = "http://guoyoyo.tech/",
        onProgressChange = { progress ->
          rememberWebViewProgress = progress
        },
        initSettings = { settings ->
          settings?.apply {
            //支持js交互
            javaScriptEnabled = true
            //将图片调整到适合webView的大小
            useWideViewPort = true
            //缩放至屏幕的大小
            loadWithOverviewMode = true
            //缩放操作
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = true
            //是否支持通过JS打开新窗口
            javaScriptCanOpenWindowsAutomatically = true
            //不加载缓存内容
            cacheMode = WebSettings.LOAD_NO_CACHE
          }
        }, onBack = { webView ->
          if (webView?.canGoBack() == true) {
            webView.goBack()
          } else {
//            finish()
          }
        }, onReceivedError = {
        }
      )
    }
  }
}
