package com.guo.awesome.comopse.chapter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guo.awesome.comopse.chapter.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val user = User()
//        user.userName.value = "Jake"
//        //读和写观察者
//        val readObserver: (Any) -> Unit = { readState ->
//            if (readState == user.userName) println("user name was read")
//        }
//        val writeObserver: (Any) -> Unit = { writtenState ->
//            if (writtenState == user.userName) println("user name was written")
//        }
//        //拍摄一个可读可写快照
//        val snapshot = Snapshot.takeMutableSnapshot(readObserver = readObserver, writeObserver = writeObserver)
//        println("name before snapshot: " + user.userName.value)
//        snapshot.enter {
//            //对userName的值进行更改
//            user.userName.value = "Elon"
//            println("name before applying: ")
//            println(user.userName.value)
//        }
//        //使用apply应用enter中的修改
//        snapshot.apply()
//        println("name after apply: ${user.userName.value}")
//        snapshot.dispose()

//        val user = User()
//        user.userName.value = "Jake"
//        Snapshot.withMutableSnapshot {
//            println(user.userName.value)
//            user.userName.value = "Elon"
//            println(user.userName.value)
//        }
//        println(user.userName.value)



        setContent {
            MyApplicationTheme(true) {
                SearchButton(modifier = Modifier.semantics { contentDescription = "点击这里开始搜索内容" })
            }
        }
    }
}

@Composable
fun ScrollingList() {

    Box {
        val listState = rememberLazyListState()

        Text("你好",
            Modifier.offset {
                IntOffset(x = 0, y = listState.firstVisibleItemScrollOffset / 2)
            }
        )

        Text("天啊",
            Modifier.offset (
                    y = with(LocalDensity.current) {
                       (listState.firstVisibleItemScrollOffset / 2).toDp()
                    }
                )
        )

        LazyColumn(state = listState) {
            items(100) {
                Text("item $it")
            }
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Row {
        Text(text = "Hello,", modifier = Modifier.align(Alignment.CenterVertically))
        Text(text = name)
    }

}

@Preview
@Composable
fun NewsStory() {
//    MyApplicationTheme(false) {
//        val typography = MaterialTheme.typography;
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Image(
//                painter = painterResource(id = R.mipmap.header),
//                contentDescription = "新闻图片",
//                modifier = Modifier
//                    .height(180.dp)
//                    .fillMaxWidth()
//                    .clip(shape = RoundedCornerShape(6.dp)),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Text(
//                text = "A day wandering through the sandhills " +
//                        "in Shark Fin Cove, and a few of the " +
//                        "sights I saw", style = typography.h6,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis
//            )
//            Text(text = "Davenport, California", style = typography.body2)
//            Text(text = "Apr 16", style = typography.body2)
//        }
//    }
}

@Composable
fun SearchButton(modifier: Modifier) {
    Row(modifier = modifier
        .background(Color.DarkGray)
        .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = android.R.drawable.ic_menu_search), contentDescription = "")
        Text(text = "搜索", fontSize = 18.sp, color = Color.White)
    }
}

@Preview
@Composable
fun TestLikeButton() {
    SearchButton(modifier = Modifier.semantics { contentDescription = "abc" })
}

@Composable
fun Foo() {
    var count by remember { mutableStateOf(0) }
    Log.d("TAG", "Foo")
    Button(modifier = Modifier.background(MaterialTheme.colors.background),onClick = {
        count += 1
    }.also { Log.d("TAG", "Button 2") }, enabled = true) {
        Log.d("TAG", "Button content lambda")
        Text("Count: $count").also { Log.d("TAG", "Text") }
    }

}

//@Preview
//@Composable
//fun Counter() {
//    var count by remember { mutableStateOf(0) }
//    Button(
//        onClick = { count += 1 }
//    ){
//        Text("Count: $count")
//    }
//}


@Preview()
@Composable
fun DefaultPreview() {
    MyApplicationTheme(false) {
        LayoutsCodelab()
    }
}

//@Composable
//fun Counter() {
//    val count = remember {
//        mutableStateOf(0)
//    }
//
//    Button(onClick = { count.value++ }) {
//        Text(text = "I've been clicked ${count.value} times.")
//    }
//}

@Composable
fun LayoutsCodelab() {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "LayoutsCodelab")
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }
            }
        )
    }) { innerPadding ->
        BodyContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    val rememberLazyListState = rememberLazyListState()
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
        with(LocalDensity.current) {
            2.toDp().toPx()
        }
        3.dp
        rememberLazyListState
    }
}

