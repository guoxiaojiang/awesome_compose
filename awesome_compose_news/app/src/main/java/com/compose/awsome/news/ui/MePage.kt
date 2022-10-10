package com.compose.awsome.news.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.awsome.news.route.RouteConfig
import com.compose.awsome.news.ui.theme.NewsTheme
import com.compose.awsome.news.ui.theme.Web3Page
import com.compose.awsome.news.viewmodel.NewsViewModel

@Composable
fun MineTopBar() {
    NewsTopBar(title = "我的")
}

@Composable
fun MinePge(viewModel: NewsViewModel, navHostController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        MineTopBar()
        Box(
          Modifier
            .background(NewsTheme.colors.listItem)
            .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "彩蛋~~~", modifier = Modifier.padding(bottom = 10.dp),  style = TextStyle(
                    color = Color.Blue,
                    fontSize = 19.sp,
                    lineHeight = 19.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Normal,
                    shadow = Shadow(
                        color = Color.Red,
                        offset = Offset(3.0f,3.0f),
                        blurRadius = 2.0f
                    )
                )
                )
                Button(onClick = {
                    navHostController.navigate(RouteConfig.ROUTE_PAGE_WEB3)
                }) {
                    Text(text = "欢迎进入Web3的世界")
                }
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