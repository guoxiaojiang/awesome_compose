package com.guo.awesome.comopse.chapter.seven

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.lang.Integer.parseInt

@Preview
@Composable
fun LearnRemind() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        var count by rememberSaveable { mutableStateOf(0) }
        var countText by remember { mutableStateOf("") }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = {
                count++
                countText = count.toString()
            }, enabled = count < 10) {
                Text("+")
            }
            Button(
                onClick = {
                    count--
                    countText = count.toString()
                },
                enabled = count > 0,
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Text("-")
            }
            Button(
                onClick = {
                    count = 0
                    countText = count.toString()
                },
                enabled = count > 0,
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Text("清空")
            }
        }
        if (count > 0) {
            var showTips by rememberSaveable { mutableStateOf(true) }
            RemindTimes(countText) {
                if (it.isEmpty()) {
                    countText = ""
                } else {
                    try {
                        val num = parseInt(it)
                        if (num in 0..10) {
                            count = num
                            countText = it
                        }
                    } catch (e: NumberFormatException) {
                    }
                }
            }
            if (showTips) {
                RemindTips(content = "提示：打开后每天会按次数进行学习提醒") {
                    showTips = false
                }
            }
        }
    }
}


@Composable
fun RemindTimes(content: String, onTimesChanged: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 5.dp)) {
        Text("当前提醒次数：")
        TextField(
            value = content,
            onValueChange = onTimesChanged,
            modifier = Modifier.width(50.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text("次")
    }
}

@Composable
fun RemindTimes(count: Int) {
    var textValue by remember { mutableStateOf(count.toString()) }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 5.dp)) {
        Text("当前提醒次数：")
        TextField(
            value = textValue,
            onValueChange = {
                if (it.isEmpty()) {
                    textValue = ""
                } else {
                    try {
                        val num = parseInt(it)
                        if (num in 0..10) {
                            textValue = it
                        }
                    } catch (e: NumberFormatException) {
                    }
                }
            },
            modifier = Modifier.width(50.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text("次")
    }
}

@Composable
fun RemindTips(content: String, onClose: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 2.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = content
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}
