package com.guo.awesome.comopse.chapter.six

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

/**
 * 演示Compose 生命周期
 */
@Composable
fun LifeCycleCounter() {
    var count by remember { mutableStateOf(0) }

    Column {
        Button(onClick = {
            count += 1
        }) {
            Text("Click to plus")
        }

        if (count in 2..3) {
            LaunchedEffect(Unit) {
                Log.d("ComposeLifeCycle", "onActive, value: :$count")
            }
            DisposableEffect(Unit) {
                onDispose {
                    Log.d("ComposeLifeCycle", "onDispose, value: $count")
                }
            }
        }

        SideEffect {
            Log.d("ComposeLifeCycle", "onChange, value: $count")
        }
        Text("Count: $count")
    }
}