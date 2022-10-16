package com.compose.awsome.news.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.compose.awsome.news.ui.theme.NewsTheme
import com.compose.awsome.news.viewmodel.AppViewModel
import com.compose.awsome.news.viewmodel.Web3ViewModel

@Composable
fun Web3PageTopBar(onBack: (() -> Unit)) {
    NewsTopBar(title = "Web3世界", onBack = onBack)
}
@Composable
fun Web3Page(viewModel: Web3ViewModel, navHostController: NavHostController) {
    val appViewModel : AppViewModel = viewModel()
    val state by viewModel.newsStateLiveData.observeAsState()
    val submitBtnState by viewModel.submitState.observeAsState()
    val readBtnState by viewModel.readState.observeAsState()
    NewsTheme(theme = appViewModel.theme) {
        LoadingPage(state = state!!,
            loadInit = {
                viewModel.initWeb3()
            }, contentView = {
                Column(Modifier.fillMaxSize()) {
                    Web3PageTopBar {
                        navHostController.navigateUp()
                    }
                    viewModel.stateStr.value?.let { Text(modifier = Modifier.padding(10.dp), style = TextStyle(
                        color = Color.Blue,
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Normal,
                    ), text = it) }
                    val textValue = remember { mutableStateOf("") }
                    TextField(modifier = Modifier.padding(10.dp), value = textValue.value, onValueChange = {
                        textValue.value = it
                    })
                    Button(modifier = Modifier.padding(10.dp).size(180.dp, 60.dp), onClick = {
                        viewModel.submit(textValue.value)
                    }, enabled = (submitBtnState == 1)) {
                        if (submitBtnState == 1) {
                            Text(text = "写入你的名字到链上")
                        } else {
                            CircularProgressIndicator()
                        }
                    }

                    Button(modifier = Modifier.padding(10.dp).size(180.dp, 60.dp), onClick = {
                        viewModel.read()
                    }, enabled = (readBtnState == 1)) {
                        if (readBtnState == 1) {
                            Text(text = "从链上读取名字")
                        } else {
                            CircularProgressIndicator()
                        }
                    }
                }
            })
    }
}