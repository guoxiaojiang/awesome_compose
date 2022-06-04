package com.compose.awsome.news.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets

private val LightColorPalette = NewsColors(
  bottomBar = white1,
  background = white2,
  listItem = white,
  divider = white3,
  textPrimary = black3,
  textPrimaryMe = black3,
  textSecondary = grey1,
  onBackground = grey3,
  icon = black,
  iconCurrent = blue
)

private val DarkColorPalette = NewsColors(
  bottomBar = black1,
  background = black2,
  listItem = black3,
  divider = black4,
  textPrimary = white4,
  textPrimaryMe = white5,
  textSecondary = grey1,
  onBackground = grey1,
  icon = white5,
  iconCurrent = blue
)

private val LocalWeColors = compositionLocalOf {
  LightColorPalette
}

@Stable
object NewsTheme {
  val colors: NewsColors
    @Composable
    get() = LocalWeColors.current

  enum class Theme {
    Light, Dark
  }
}

@Stable
class NewsColors(
  bottomBar: Color,
  background: Color,
  listItem: Color,
  divider: Color,
  textPrimary: Color,
  textPrimaryMe: Color,
  textSecondary: Color,
  onBackground: Color,
  icon: Color,
  iconCurrent: Color
) {
  var bottomBar: Color by mutableStateOf(bottomBar)
    private set
  var background: Color by mutableStateOf(background)
    private set
  var listItem: Color by mutableStateOf(listItem)
    private set
  var chatListDivider: Color by mutableStateOf(divider)
    private set
  var textPrimary: Color by mutableStateOf(textPrimary)
    private set
  var textPrimaryMe: Color by mutableStateOf(textPrimaryMe)
    private set
  var textSecondary: Color by mutableStateOf(textSecondary)
    private set
  var onBackground: Color by mutableStateOf(onBackground)
    private set
  var icon: Color by mutableStateOf(icon)
    private set
  var iconCurrent: Color by mutableStateOf(iconCurrent)
    private set
}

@Composable
fun NewsTheme(theme: NewsTheme.Theme = NewsTheme.Theme.Light, content: @Composable () -> Unit) {
  val targetColors = when (theme) {
    NewsTheme.Theme.Light -> LightColorPalette
    NewsTheme.Theme.Dark -> DarkColorPalette
  }
  val bottomBar = animateColorAsState(targetColors.bottomBar, TweenSpec(600))
  val background = animateColorAsState(targetColors.background, TweenSpec(600))
  val listItem = animateColorAsState(targetColors.listItem, TweenSpec(600))
  val chatListDivider = animateColorAsState(targetColors.chatListDivider, TweenSpec(600))
  val textPrimary = animateColorAsState(targetColors.textPrimary, TweenSpec(600))
  val textPrimaryMe = animateColorAsState(targetColors.textPrimaryMe, TweenSpec(600))
  val textSecondary = animateColorAsState(targetColors.textSecondary, TweenSpec(600))
  val onBackground = animateColorAsState(targetColors.onBackground, TweenSpec(600))
  val icon = animateColorAsState(targetColors.icon, TweenSpec(600))
  val iconCurrent = animateColorAsState(targetColors.iconCurrent, TweenSpec(600))
  val colors = NewsColors(
    bottomBar = bottomBar.value,
    background = background.value,
    listItem = listItem.value,
    divider = chatListDivider.value,
    textPrimary = textPrimary.value,
    textPrimaryMe = textPrimaryMe.value,
    textSecondary = textSecondary.value,
    onBackground = onBackground.value,
    icon = icon.value,
    iconCurrent = iconCurrent.value,
  )

  CompositionLocalProvider(LocalWeColors provides colors) {
    MaterialTheme(
      shapes = shapes
    ) {
      ProvideWindowInsets(content = content)
    }
  }
}