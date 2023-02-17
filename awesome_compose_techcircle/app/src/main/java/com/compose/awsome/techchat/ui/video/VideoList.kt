package com.compose.awsome.techchat.ui.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.compose.awsome.techchat.ui.theme.NewsTheme
import com.compose.awsome.techchat.viewmodel.VideosViewModel
import com.compose.awsome.techchat.R
import com.compose.awsome.techchat.model.video.VideoItem
import com.compose.awsome.techchat.ui.AwesomeTechTopBar
import com.compose.awsome.techchat.ui.LoadingPage
import com.compose.awsome.techchat.viewmodel.AppViewModel

@Composable
fun VideoListTopBar() {
    AwesomeTechTopBar(title = "视频头条")
}

@Preview
@Composable
fun VideoItemPreview() {
    val item = VideoItem(
        "【安全生产】竹林寺管理区开展“安全生产月”主题宣传活动 - 今日头条",
        "https://guoxj.oss-cn-beijing.aliyuncs.com/compose/file/video_cover1.png?Expires=1654448514&OSSAccessKeyId=TMP.3KiJqVDE8PVj1P7dDgVRWLcHZAne8XymdQ3bjXt2r84Scnr9C1eRHNJqpNXxtgN1Jj8FymJHkmM71ikJRVDy51Mg8T8n97&Signature=ptGOQIuzhGToIJAUP2oSv7MjuGs%3D",
        author = "疯狂兔子",
        "https://guoxj.oss-cn-beijing.aliyuncs.com/compose/file/video_cover3.png?Expires=1654448762&OSSAccessKeyId=TMP.3KiJqVDE8PVj1P7dDgVRWLcHZAne8XymdQ3bjXt2r84Scnr9C1eRHNJqpNXxtgN1Jj8FymJHkmM71ikJRVDy51Mg8T8n97&Signature=%2B8FYP%2BfbB%2BYUBCgWRXZQVBP9%2F%2Bg%3D",
        "https://guoxj.oss-cn-beijing.aliyuncs.com/compose/file/video1.mp4?Expires=1654448719&OSSAccessKeyId=TMP.3KiJqVDE8PVj1P7dDgVRWLcHZAne8XymdQ3bjXt2r84Scnr9C1eRHNJqpNXxtgN1Jj8FymJHkmM71ikJRVDy51Mg8T8n97&Signature=hrTcgtFHAmQhRb6Daz2tIbiUOx0%3D"
    )
//    VideoListItem(item)
}

@Composable
fun VideoListItem(
    item: VideoItem,
    viewModel: VideosViewModel,
    index: Int,
    pauseIconVisibleState: MutableState<Boolean>,
    pageInVisible: MutableState<Boolean>
) {

    Column(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = {
            })
    ) {
        Text(
            item.title,
            fontSize = 16.sp,
            color = NewsTheme.colors.textPrimary,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 5.dp, start = 8.dp, end = 8.dp)
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = item.avatar),
                contentDescription = "avatar", modifier = Modifier
                    .padding(12.dp, 4.dp, 8.dp, 4.dp)
                    .size(30.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                item.author,
                fontSize = 12.sp,
                color = NewsTheme.colors.textPrimaryMe,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 5.dp)
            )
        }

        Box() {
            Image(
                painter = rememberImagePainter(data = item.pic),
                contentDescription = "pic", modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            VideoPlayer(item.videoUrl, viewModel.playListIndex, index, pauseIconVisibleState, pageInVisible)
        }


        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.mipmap.icon_share),
                contentDescription = "分享",
                modifier = Modifier
                    .height(18.dp)
                    .width(18.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                "分享",
                fontSize = 12.sp,
                color = NewsTheme.colors.textSecondary,
                maxLines = 1,
                modifier = Modifier.padding(start = 3.dp, end = 28.dp)
            )

            Image(
                painter = painterResource(R.mipmap.icon_collect),
                contentDescription = "收藏",
                modifier = Modifier
                    .height(18.dp)
                    .width(18.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                "收藏",
                fontSize = 12.sp,
                color = NewsTheme.colors.textSecondary,
                maxLines = 1,
                modifier = Modifier.padding(start = 3.dp, end = 28.dp)
            )

            Image(
                painter = painterResource(R.mipmap.icon_comment),
                contentDescription = "评论",
                modifier = Modifier
                    .height(18.dp)
                    .width(18.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                "102",
                fontSize = 12.sp,
                color = NewsTheme.colors.textSecondary,
                maxLines = 1,
                modifier = Modifier.padding(start = 3.dp, end = 28.dp)
            )

            Image(
                painter = painterResource(R.mipmap.icon_like),
                contentDescription = "赞",
                modifier = Modifier
                    .height(18.dp)
                    .width(18.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                "9022",
                fontSize = 12.sp,
                color = NewsTheme.colors.textSecondary,
                maxLines = 1,
                modifier = Modifier.padding(start = 3.dp)
            )

        }
    }
}

@Composable
fun VideoList(videos: List<VideoItem>, viewModel: VideosViewModel) {
    val listState = rememberLazyListState()
    if (listState.isScrollInProgress) {
        LaunchedEffect(Unit){
            //只会调用一次，相当于滚动开始
        }
        //当state处于滚动时，preScrollStartOffset会被初始化并记忆,不会再被更改
        val preScrollStartOffset by remember { mutableStateOf(listState.firstVisibleItemScrollOffset) }
        val preItemIndex by remember { mutableStateOf(listState.firstVisibleItemIndex) }
        val isScrollDown = if (listState.firstVisibleItemIndex > preItemIndex) {
            //第一个可见item的index大于开始滚动时第一个可见item的index，说明往下滚动了
            true
        } else if (listState.firstVisibleItemIndex < preItemIndex) {
            //第一个可见item的index小于开始滚动时第一个可见item的index，说明往上滚动了
            false
        } else {
            //第一个可见item的index等于开始滚动时第一个可见item的index,对比item offset
            listState.firstVisibleItemScrollOffset > preScrollStartOffset
        }

        DisposableEffect(Unit) {
            onDispose {
                //当dispose时说明list不再处于滚动状态，刚好是滚动结束
                //onScroll Done
                viewModel.playListIndex.value = listState.firstVisibleItemIndex
            }
        }
    }
    LazyColumn(
        Modifier
            .background(NewsTheme.colors.listItem)
            .fillMaxWidth(),
        listState
    ) {
        itemsIndexed(videos) { index, order ->

            val pauseIconVisibleState = mutableStateOf(true)

            if (index == viewModel.playListIndex.value) {
                pauseIconVisibleState.value = false
            }

            val pageInVisible = mutableStateOf(false)
            val appViewModel: AppViewModel = viewModel()
            pageInVisible.value = appViewModel.currentPage == 1

            VideoListItem(order, viewModel, index, pauseIconVisibleState, pageInVisible)
            if (index < videos.size - 1) {
                Divider(
                    startIndent = 8.dp,
                    color = NewsTheme.colors.chatListDivider,
                    thickness = 0.8f.dp
                )
            }
        }
    }
}

@Composable
fun VideoList(viewModel: VideosViewModel) {

    val state by viewModel.awesomeTechStateLiveData.observeAsState()
    val videosModel by viewModel.videoLiveData.observeAsState()

    LoadingPage(state = state!!,
        loadInit = {
            viewModel.fetchVideoList()
        }, contentView = {
            Column(Modifier.fillMaxSize()) {
                VideoListTopBar()
                Box(
                    Modifier
                        .background(NewsTheme.colors.background)
                        .fillMaxSize()
                ) {
                    videosModel?.data?.let { VideoList(it.videos, viewModel) }
                }
            }
        })

}