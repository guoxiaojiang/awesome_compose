package com.compose.awsome.techchat.ui.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.compose.awsome.techchat.ui.AwesomeTechTopBar
import com.compose.awsome.techchat.viewmodel.AppViewModel

@Composable
fun ArticleDetail(navHostController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        AwesomeTechTopBar(title = "", onBack = {navHostController.navigateUp()})
        Text("文章标题")
        Text("文章内容")
        Text("其它")
    }
    val appViewModel: AppViewModel = viewModel()
//    AwesomeTechTheme(theme = appViewModel.theme) {
//        LoadingPage(state = state!!,
//            loadInit = {
//                viewModel.initWeb3()
//            }, contentView = {
//                Column(Modifier.fillMaxSize()) {
//                    AwesomeTechTopBar(title = "", onBack = { navHostController.navigateUp() })
//                    viewModel.stateStr.value?.let {
//                        Text(
//                            modifier = Modifier.padding(10.dp), style = TextStyle(
//                                color = Color.Blue,
//                                fontSize = 18.sp,
//                                lineHeight = 18.sp,
//                                fontWeight = FontWeight.Bold,
//                                fontFamily = FontFamily.Serif,
//                                fontStyle = FontStyle.Normal,
//                            ), text = it
//                        )
//                    }
//                    val textValue = remember { mutableStateOf("") }
//                    TextField(
//                        modifier = Modifier.padding(10.dp),
//                        value = textValue.value,
//                        onValueChange = {
//                            textValue.value = it
//                        })
//                    Button(modifier = Modifier
//                        .padding(10.dp)
//                        .size(180.dp, 60.dp), onClick = {
//                        viewModel.submit(textValue.value)
//                    }, enabled = (submitBtnState == 1)) {
//                        if (submitBtnState == 1) {
//                            Text(text = "写入你的名字到链上")
//                        } else {
//                            CircularProgressIndicator()
//                        }
//                    }
//
//                    Button(modifier = Modifier
//                        .padding(10.dp)
//                        .size(180.dp, 60.dp), onClick = {
//                        viewModel.read()
//                    }, enabled = (readBtnState == 1)) {
//                        if (readBtnState == 1) {
//                            Text(text = "从链上读取名字")
//                        } else {
//                            CircularProgressIndicator()
//                        }
//                    }
//                }
//            })
//    }
}