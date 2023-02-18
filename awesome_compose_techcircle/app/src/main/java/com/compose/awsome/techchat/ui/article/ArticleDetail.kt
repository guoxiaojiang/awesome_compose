package com.compose.awsome.techchat.ui.article

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.compose.awsome.techchat.R
import com.compose.awsome.techchat.model.article.ArticleDetail
import com.compose.awsome.techchat.ui.AwesomeTechTopBar
import com.compose.awsome.techchat.ui.LoadingPage
import com.compose.awsome.techchat.ui.theme.AwesomeTechTheme
import com.compose.awsome.techchat.viewmodel.AppViewModel
import com.compose.awsome.techchat.viewmodel.ArticleViewModel
import com.dmytroshuba.dailytags.core.simple.SimpleMarkupParser
import com.dmytroshuba.dailytags.core.simple.render
import com.dmytroshuba.dailytags.markdown.rules.MarkdownRules

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ArticleDetail(articleDetail: ArticleDetail, viewModel: ArticleViewModel) {

    Box(
        Modifier.fillMaxSize()
    ) {
        var transitionState by remember {
            mutableStateOf(MutableTransitionState(LikedStates.Disappeared))
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 55.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                articleDetail.title,
                modifier = Modifier.padding(8.dp),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = AwesomeTechTheme.colors.textPrimary,
                maxLines = 2
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(data = articleDetail.avatar),
                    contentDescription = "avatar", modifier = Modifier
                        .padding(end = 3.dp)
                        .size(30.dp)
                        .clip(RoundedCornerShape(15.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    articleDetail.author,
                    fontSize = 14.sp,
                    color = AwesomeTechTheme.colors.textPrimaryMe,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 3.dp)
                )

                Spacer(Modifier.weight(1f))

                Text(
                    articleDetail.date,
                    fontSize = 12.sp,
                    color = AwesomeTechTheme.colors.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 3.dp)
                )

            }

            Text(
                articleDetail.intro,
                modifier = Modifier
                    .padding(8.dp)
                    .background(AwesomeTechTheme.colors.introBG)
                    .padding(4.dp),
                fontSize = 16.sp,
                color = AwesomeTechTheme.colors.textPrimary
            )

            Image(
                painter = rememberImagePainter(data = articleDetail.pic),
                contentDescription = "pic", modifier = Modifier
                    .padding(8.dp, 4.dp, 8.dp, 4.dp)
                    .height(160.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            val rules = MarkdownRules.toList()
            val parser = SimpleMarkupParser()
            val content = parser
                .parse(articleDetail.content, rules)
                .render()
                .toAnnotatedString()

            Text(
                content,
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp,
                color = AwesomeTechTheme.colors.textPrimary,
            )

            Text("其它")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AwesomeTechTheme.colors.bottomBar)
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(AwesomeTechTheme.colors.textSecondary)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 8.dp, top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .padding(end = 8.dp)
                        .background(AwesomeTechTheme.colors.introBG)
                        .padding(6.dp)
                ) {
                    Text(
                        "说点什么...",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 12.sp,
                        color = AwesomeTechTheme.colors.textSecondary
                    )

                }
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painterResource(R.mipmap.ic_article_detail_comment),
                    "评论",
                    Modifier
                        .align(Alignment.CenterVertically)
                        .size(34.dp)
                        .padding(6.dp, end = 2.dp),
                    tint = AwesomeTechTheme.colors.icon
                )

                Text(
                    text = "99+",
                    fontSize = 8.sp,
                    color = AwesomeTechTheme.colors.textSecondary,
                    modifier = Modifier.padding(end = 2.dp, bottom = 10.dp)
                )

                var shake by remember { mutableStateOf(false) }
                val transition = updateTransition(targetState = shake, label = "shake")
                val shakeOffset by transition.animateDp(
                    transitionSpec = {
                        keyframes {
                            durationMillis = 200
                            0.dp at 0
                            (-10).dp at 25 with LinearOutSlowInEasing
                            0.dp at 50
                            10.dp at 75
                            0.dp at 100
                            (-8).dp at 125
                            0.dp at 150
                            8.dp at 175
                            0.dp at 200
                            (-5).dp at 225
                            0.dp at 250
                            5.dp at 275
                            0.dp at 300
                        }
                    }, label = "shakeOffset"
                ) {
                    0.dp
                }

                val submitState by viewModel.submitState.observeAsState()
                Icon(
                    painterResource(R.mipmap.ic_article_detail_like),
                    "点赞",
                    Modifier
                        .align(Alignment.CenterVertically)
                        .size(34.dp)
                        .padding(6.dp, end = 2.dp).graphicsLayer(
                            translationX = shakeOffset.value
                        )
                        .clickable {
                            if (submitState == 1) {
                                transitionState = MutableTransitionState(LikedStates.Initial)
                                viewModel.doLike()
                            } else {
                                shake = !shake
                            }
                        },
                    tint = AwesomeTechTheme.colors.icon
                )


                val textContent =
                    if (submitState == 0) "biu~" else if (viewModel.currentLikeCount <= 999) viewModel.currentLikeCount.toString() else "999+"
                Text(
                    text = textContent,
                    fontSize = 8.sp,
                    color = AwesomeTechTheme.colors.textSecondary,
                    modifier = Modifier.padding(end = 2.dp, bottom = 10.dp)
                )

                Icon(
                    painterResource(R.mipmap.ic_article_detail_collect),
                    "收藏",
                    Modifier
                        .align(Alignment.CenterVertically)
                        .size(34.dp)
                        .padding(6.dp),
                    tint = AwesomeTechTheme.colors.icon
                )

                Icon(
                    painterResource(R.mipmap.ic_article_detail_share),
                    "分享",
                    Modifier
                        .align(Alignment.CenterVertically)
                        .size(34.dp)
                        .padding(6.dp),
                    tint = AwesomeTechTheme.colors.icon
                )
            }
        }
        Box(
            Modifier.fillMaxSize()
        ) {
            if (transitionState.currentState == LikedStates.Initial) {
                transitionState.targetState = LikedStates.Liked
            } else if (transitionState.currentState == LikedStates.Liked) {
                transitionState.targetState = LikedStates.Disappeared
            }

            val transition = updateTransition(transitionState = transitionState, label = null)
            val alpha by transition.animateFloat(
                transitionSpec = {
                    when {
                        LikedStates.Initial isTransitioningTo LikedStates.Liked ->
                            keyframes {
                                durationMillis = 500
                                0f at 0
                                0.5f at 100
                                1f at 225
                            }
                        LikedStates.Liked isTransitioningTo LikedStates.Disappeared ->
                            tween(durationMillis = 200)
                        else -> snap()
                    }
                }, label = "alpha"
            ) {
                if (it == LikedStates.Liked) 1f else 0f
            }

            val scale by transition.animateFloat(
                transitionSpec = {
                    when {
                        LikedStates.Initial isTransitioningTo LikedStates.Liked ->
                            spring(dampingRatio = Spring.DampingRatioHighBouncy)
                        LikedStates.Liked isTransitioningTo LikedStates.Disappeared ->
                            tween(200)
                        else -> snap()
                    }
                }, label = "scale"
            ) {
                when (it) {
                    LikedStates.Initial -> 0f
                    LikedStates.Liked -> 4f
                    LikedStates.Disappeared -> 2f
                }
            }

            Icon(
                Icons.Filled.Favorite,
                "点赞",
                Modifier
                    .align(Alignment.Center)
                    .graphicsLayer(
                        alpha = alpha,
                        scaleX = scale,
                        scaleY = scale
                    ),
                tint = Color.Red
            )
        }
    }
}

enum class LikedStates {
    Initial,
    Liked,
    Disappeared
}

@Composable
fun ArticleDetail(navHostController: NavHostController, articleID: Long) {
    val articleViewModel: ArticleViewModel = viewModel()
    val state by articleViewModel.awesomeTechStateLiveData.observeAsState()
    val articleDetailModel by articleViewModel.articleDetailLiveData.observeAsState()
    val appViewModel: AppViewModel = viewModel()
    AwesomeTechTheme(theme = appViewModel.theme) {
        LoadingPage(state = state!!,
            loadInit = {
                articleViewModel.initWeb3()
                articleViewModel.fetchArticleDetail(articleID)
                articleViewModel.readLikeCount()
            }, contentView = {
                Column(Modifier.fillMaxSize()) {
                    AwesomeTechTopBar(onBack = { navHostController.navigateUp() })
                    Box(
                        Modifier
                            .background(AwesomeTechTheme.colors.listItem)
                            .fillMaxSize()
                    ) {
                        articleDetailModel?.let { ArticleDetail(it.data, articleViewModel) }
                    }
                }
            })
    }
}