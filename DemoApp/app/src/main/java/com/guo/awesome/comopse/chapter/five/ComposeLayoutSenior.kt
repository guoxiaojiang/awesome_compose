package com.guo.awesome.comopse.chapter.five

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import com.guo.awesome.comopse.chapter.R

@Composable
fun MyCustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables: List<Measurable>, constraints: Constraints ->
        TODO("测量和放置项目")
    }
}

@Composable
fun MyCustomColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            // 根据父项的约束条件测量子项
            measurable.measure(constraints)
        }

        // 按约束条件的最大值设置布局的尺寸
        layout(constraints.maxWidth, constraints.maxHeight) {
            // 用于记录已经放置的子项的 y 坐标值
            var yPosition = 0

            // 放置每一个子项
            placeables.forEach { placeable ->
                // 根据子项的起始坐标放置其位置
                placeable.placeRelative(x = 0, y = yPosition)

                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun TestCustomLayout(modifier: Modifier = Modifier) {
    MyCustomColumn(modifier.padding(8.dp)) {
        Text("这是一个自定义Column布局")
        Text("纵向排列")
        Text("所有子元素")
        Text("We've done it by hand!")
    }
}


fun Modifier.padding(all: Dp) =
    this.then(
        PaddingModifier(
            start = all,
            top = all,
            end = all,
            bottom = all,
            rtlAware = true
        )
    )

private class PaddingModifier(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val rtlAware: Boolean
) : LayoutModifier {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {

        val horizontal = start.roundToPx() + end.roundToPx()
        val vertical = top.roundToPx() + bottom.roundToPx()

        // 按 padding 尺寸收缩外部约束来修改测量
        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))

        // 计算布局的最大宽度和高度值
        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)
        return layout(width, height) {
            // 按所需的 padding 执行偏移以放置内容
            if (rtlAware) {
                placeable.placeRelative(start.roundToPx(), top.roundToPx())
            } else {
                placeable.place(start.roundToPx(), top.roundToPx())
            }
        }
    }
}


fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = layout { measurable, constraints ->
    // 根据约束条件测量可组合项
    val placeable = measurable.measure(constraints)

    // 检查可组合项是否有第一行文本基线
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // 计算可组合项的放置位置y坐标和高度
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        // 放置可组合项
        placeable.placeRelative(0, placeableY)
    }
}

@Composable
fun TestCustomModifier() {
    Row(
        Modifier.padding(start = 8.dp)
    ) {
        Text(text = "Custom Modifier!", modifier = Modifier.firstBaselineToTop(32.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Normal Modifier!", modifier = Modifier.padding(0.dp, 32.dp))
    }
}


@Composable
fun MessageList(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageItem(message)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ListWithHeader(items: List<Message>) {
    LazyColumn {
        stickyHeader {
            MessageHeader()
        }
        items(items) { item ->
            MessageItem(item)
        }
    }
}

@Composable
fun MessageHeader() {
    TODO()
}

@Composable
fun MessageItem(msg: Message) {
    TODO()
}

data class Contact(var name: String, var desc: String?)

@ExperimentalFoundationApi
@Composable
fun TestContactList() {
    val contactMap = HashMap<Char, List<Contact>>()
    val firstLetterList = arrayOf('B', 'C', 'D', 'F', 'G', 'H', 'L', 'P')
    firstLetterList.forEach {
        val list = arrayListOf<Contact>()
        repeat((1..10).random()) { index ->
            list.add(Contact("$it-name$index", "description of $it-name$index"))
        }
        contactMap[it] = list
    }
    ContactList(contactMap)
}


@ExperimentalFoundationApi
@Composable
fun ContactList(group: Map<Char, List<Contact>>) {
    LazyColumn {
        group.forEach { (first, contacts) ->
            stickyHeader {
                ContactHeader(first)
            }
            items(contacts) { item ->
                ContactItem(item)
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp), contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Column {
            Text(text = contact.name)
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
            contact.desc?.let { Text(text = it) }
        }
    }
}

@Composable
fun ContactHeader(firstLetter: Char) {
    Text(
        text = firstLetter.toString(),
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}


@ExperimentalFoundationApi
@Composable
fun PhotoGrid(photos: List<Photo>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(photos) { photo ->
            PhotoItem(photo)
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) {
    TODO()
}