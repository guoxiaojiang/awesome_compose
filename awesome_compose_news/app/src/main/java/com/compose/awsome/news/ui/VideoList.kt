package com.compose.awsome.news.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import coil.compose.rememberImagePainter
import com.compose.awsome.news.R
import com.compose.awsome.news.data.VideoItem
import com.compose.awsome.news.ui.theme.NewsTheme
import com.compose.awsome.news.viewmodel.VideosViewModel

@Composable
fun VideoListTopBar() {
    NewsTopBar(title = "视频头条")
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
    VideoListItem(item)
}

@Composable
fun VideoListItem(
    item: VideoItem
) {

    Column(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = {
            })
    ) {
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

        Text(
            item.title,
            fontSize = 18.sp,
            color = NewsTheme.colors.textPrimary,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 5.dp, start = 8.dp, end = 8.dp, bottom = 5.dp)
        )

        Image(
            painter = rememberImagePainter(data = item.pic),
            contentDescription = "pic", modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

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
fun VideoList(videos: List<VideoItem>) {
    LazyColumn(
        Modifier
            .background(NewsTheme.colors.listItem)
            .fillMaxWidth()
    ) {
        itemsIndexed(videos) { index, order ->
            VideoListItem(order)
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

    val state by viewModel.newsStateLiveData.observeAsState()
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
                    videosModel?.data?.let { VideoList(it.videos) }
                }
            }
        })

}