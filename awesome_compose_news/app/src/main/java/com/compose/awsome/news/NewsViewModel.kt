package com.compose.awsome.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.awsome.news.data.Order
import com.compose.awsome.news.data.Poi
import com.compose.awsome.news.ui.theme.NewsTheme

class NewsViewModel : ViewModel() {
  var poiList by mutableStateOf(
    listOf(
      Poi("金枪鱼紫菜包饭", "月售 2133 好评率99%", "￥18", R.mipmap.header),
      Poi("便宜坊", "月售 133 好评率98%", "￥98", R.mipmap.header),
      Poi("凉皮先生.凉皮.肉夹馍.酸辣粉.各种各样.面食等等省略号", "月售 166 好评率93%", "￥22", R.mipmap.header),
      Poi("小恒水饺（望京SOHO店）", "月售 87 好评率91%", "￥17", R.mipmap.header),
    )
  )

  var orderList by mutableStateOf(
    listOf(
      Order("金枪鱼紫菜包饭", "￥18", R.mipmap.header),
      Order("便宜坊",  "￥98", R.mipmap.header),
      Order("凉皮先生.凉皮.肉夹馍.酸辣粉.各种各样.面食等等省略号", "￥22", R.mipmap.header),
      Order("小恒水饺（望京SOHO店）", "￥17", R.mipmap.header),
    )
  )
  var theme by mutableStateOf(NewsTheme.Theme.Light)
}