package com.compose.awsome.techchat.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.awsome.techchat.route.RouteConfig
import com.compose.awsome.techchat.ui.theme.AwesomeTechTheme
import com.compose.awsome.techchat.viewmodel.ArticleViewModel

@Composable
fun FriendCircleTopBar() {
    AwesomeTechTopBar(title = "我的")
}

@Composable
fun FriendCirclePge(viewModel: ArticleViewModel, navHostController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        MineTopBar()
        Box(
          Modifier
            .background(AwesomeTechTheme.colors.listItem)
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