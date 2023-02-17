package com.compose.awsome.techchat.ui.video

import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.MutableLiveData
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.compose.awsome.techchat.R

@Composable
fun VideoPlayer(
    videoUrl: String,
    currentFocusIndex: MutableState<Int>,
    index: Int,
    pauseIconVisibleState: MutableState<Boolean>,
    pageInVisible: MutableState<Boolean>
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context)
            .build()
            .apply {
                val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                    context,
                    Util.getUserAgent(context, context.packageName)
                )
                val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(videoUrl))

                this.prepare(source)
            }
    }

    if (index == currentFocusIndex.value && !pauseIconVisibleState.value && pageInVisible.value) {
        exoPlayer.playWhenReady = true
        exoPlayer.play()
    } else {
        exoPlayer.pause()
    }
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

    DisposableEffect(
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(factory = {
                PlayerView(context).apply {
                    hideController()
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }, modifier = Modifier.clickable {
                if (exoPlayer.isPlaying) {
                    pauseIconVisibleState.value = true
                    exoPlayer.pause()
                } else {
                    currentFocusIndex.value = index
                    pauseIconVisibleState.value = false
                }
            })
            if (pauseIconVisibleState.value)
                Image(
                    painterResource(R.mipmap.ic_player_play),
                    null,
                    Modifier
                        .align(Alignment.Center)
                        .size(60.dp)
                )
        }
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}