package com.compose.awsome.news.data
import androidx.annotation.DrawableRes

data class Order(
  val name: String,
  val price: String,
  @DrawableRes val pic: Int
)