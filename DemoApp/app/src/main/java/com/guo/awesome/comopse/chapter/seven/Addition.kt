package com.guo.awesome.comopse.chapter.seven

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Addition() {
    var count by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.LightGray).padding(20.dp)) {
        Text("Count: $count")
        Button(modifier = Modifier.padding(top = 10.dp),onClick = {
            count += 1
        }) {
            Text("+1")
        }
    }
}

@Composable
fun Addition(count : Int, clickHandler: ()->Unit) {
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.LightGray).padding(20.dp)) {
        Text("Count: $count")
        Button(modifier = Modifier.padding(top = 10.dp),
            onClick = clickHandler) {
            Text("+1")
        }
    }
}