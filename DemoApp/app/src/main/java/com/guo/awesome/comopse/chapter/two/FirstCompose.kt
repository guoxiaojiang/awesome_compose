package com.guo.awesome.comopse.chapter.two

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SayHello() {
    Column {
        Greeting(name = "World!")
        Greeting(name = "Compose")
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name",
        color = Color(0xFFFF1048),
        fontSize = 36.sp,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
    )
    Log.d("Compose", "Hello $name")
}