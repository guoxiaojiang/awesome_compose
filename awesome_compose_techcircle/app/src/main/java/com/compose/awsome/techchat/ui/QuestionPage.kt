package com.compose.awsome.techchat.ui

import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.compose.awsome.techchat.ui.theme.NewsTheme

@Composable
fun QuestionTopBar() {
    AwesomeTechTopBar(title = "问答")
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    Text(text = "问答")
}

@Composable
fun QuestionPage(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        QuestionTopBar()
        Box(
            Modifier
                .background(NewsTheme.colors.background)
                .fillMaxSize()
        ) {
            AwesomeTechWebView(
                modifier = Modifier.fillMaxSize(),
                url = "https://zhihu.com",
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
                        onBack()
                    }
                }
            )
        }
    }
}
