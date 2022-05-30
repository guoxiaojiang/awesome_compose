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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.awsome.news.NewsViewModel
import com.compose.awsome.news.data.Order
import com.compose.awsome.news.ui.theme.NewsTheme

@Composable
fun OrderListTopBar() {
  NewsTopBar(title = "订单列表")
}

@Composable
fun OrderListItem(
  order: Order
) {
  Row(
    Modifier
      .fillMaxWidth()
      .clickable(onClick = {
      })
  ) {
    Image(
      painterResource(order.pic), "pic", Modifier
        .padding(12.dp, 8.dp, 8.dp, 8.dp)
        .size(48.dp)
        .clip(RoundedCornerShape(4.dp))
    )
    Column(
      Modifier
        .weight(1f)
        .align(Alignment.CenterVertically)
    ) {
      Text(order.name, fontSize = 16.sp, color = NewsTheme.colors.textPrimary,  maxLines = 1)
      Text(order.price, fontSize = 14.sp, color = NewsTheme.colors.textSecondary)
    }
    Text(
      "2021-06-09 13:48",
      Modifier.padding(8.dp, 8.dp, 12.dp, 8.dp),
      fontSize = 11.sp, color = NewsTheme.colors.textSecondary
    )
  }
}

@Composable
fun VideoList(orders: List<Order>) {
  LazyColumn(
    Modifier
      .background(NewsTheme.colors.listItem)
      .fillMaxWidth()
  ) {
    itemsIndexed(orders) { index, order ->
      OrderListItem(order)
      if (index < orders.size - 1) {
        Divider(startIndent = 68.dp, color = NewsTheme.colors.chatListDivider, thickness = 0.8f.dp)
      }
    }
  }
}

@Composable
fun VideoList(viewModel: NewsViewModel) {
  Column(Modifier.fillMaxSize()) {
    OrderListTopBar()
    Box(
      Modifier
        .background(NewsTheme.colors.background)
        .fillMaxSize()
    ) {
      VideoList(orders = viewModel.orderList)
    }
  }
}