package com.guo.awesome.comopse.chapter.seven

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//@Parcelize
//data class Article(val title: String, val body: String) : Parcelable
//
//@Composable
//fun ArticleItem() {
//    var article = rememberSaveable {
//        mutableStateOf(Article("震惊！移动端开发还可以这样写", "这里是技术文章的内容"))
//    }
//}

data class Article(val title: String, val body: String)

//val ArticleSaver = run {
//    val titleKey = "Title"
//    val bodyKey = "Body"
//    mapSaver(
//        save = { mapOf(titleKey to it.title, bodyKey to it.body) },
//        restore = { Article(it[titleKey] as String, it[bodyKey] as String) }
//    )
//}
//
//@Composable
//fun ArticleItem() {
//    var article = rememberSaveable {
//        mutableStateOf(Article("震惊！移动端开发还可以这样写", "这里是技术文章的内容"))
//    }
//}

val ArticleSaver = listSaver<Article, Any>(
    save = { listOf(it.title, it.body) },
    restore = { Article(it[0] as String, it[1] as String) }
)

@Composable
fun ArticleItem() {
    var article = rememberSaveable(stateSaver = ArticleSaver) {
        mutableStateOf(Article("震惊！移动端开发还可以这样写", "这里是技术文章的内容"))
    }
}

@Composable
fun CheckableItem(
    name: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = name
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun CheckableItem(name: String, modifier: Modifier = Modifier) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    CheckableItem(
        name = name,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        modifier = modifier,
    )
}