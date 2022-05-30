package com.compose.awsome.news.data

import androidx.annotation.DrawableRes

data class Poi(
  val name: String,
  val desc: String,
  val price: String,
  @DrawableRes val pic: Int
)