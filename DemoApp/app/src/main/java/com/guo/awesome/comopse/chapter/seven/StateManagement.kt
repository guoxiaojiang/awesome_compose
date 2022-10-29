package com.guo.awesome.comopse.chapter.seven

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AlertArea() {

    var openDialog by remember { mutableStateOf(false) }
    Button(onClick = { openDialog = true }) {
        Text(text = "请谨慎操作")
    }

    if (openDialog) {
        AlertDialog(
            modifier = Modifier.padding(20.dp),
            onDismissRequest = { openDialog = false },
            title = {
                Text(
                    text = "您确认吗？",
                    style = MaterialTheme.typography.h4
                )
            },
            text = {
                Text(
                    text = "这里是提示的内容",
                    style = MaterialTheme.typography.body1
                )
            },
            confirmButton = {
                Text(
                    text = "确认",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { openDialog = false }
                )
            }
        )
    }
}