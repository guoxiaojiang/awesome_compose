package com.compose.awsome.techchat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.compose.awsome.techchat.model.DataState
import com.compose.awsome.techchat.R

@Composable
fun LoadingPage(
    state: DataState,
    loadInit: (() -> Unit)? = null,
    contentView: @Composable BoxScope.() -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.isLoading() -> {
                loadInit?.invoke()
                CircularProgressIndicator()
            }
            state.isError() -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            loadInit?.invoke()
                        }
                ) {
                    Image(painterResource(R.mipmap.ic_network_error), null, Modifier.size(80.dp))
                    Text((state as DataState.Error).errorMsg.toString())
                }
            }
            else -> {
                contentView()
            }
        }
    }
}