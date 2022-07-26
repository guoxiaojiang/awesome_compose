package com.guo.awesome.comopse.chapter.four

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//*******************扩展Material主题系统*********************//
/**
 * 自定义颜色
 */
@Immutable
data class ExtendedColors(
    val tertiary: Color,
    val onTertiary: Color
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        tertiary = Color.Unspecified,
        onTertiary = Color.Unspecified
    )
}

/**
 * 使用自定义颜色设置主题
 */
@Composable
fun ExtendedTheme(
    /* ... */
    content: @Composable () -> Unit
) {
    val extendedColors = ExtendedColors(
        tertiary = Color(0xFFA8EFF0),
        onTertiary = Color(0xFF002021)
    )
    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            /* colors = ..., typography = ..., shapes = ... */
            content = content
        )
    }
}

// Use with eg. ExtendedTheme.colors.tertiary
object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}

/**
 * 在自定义Button中使用自定义的颜色系统
 */
@Composable
fun ExtendedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ExtendedTheme.colors.tertiary,
            contentColor = ExtendedTheme.colors.onTertiary
            /* Other colors use values from MaterialTheme */
        ),
        onClick = onClick,
        modifier = modifier,
        content = content
    )
}


//*********************替换Material主题系统****************************//
/**
 * 通过替换排版系统来替换主题
 */
@Immutable
data class ReplacementTypography(
    val body: TextStyle,
    val title: TextStyle
)

val LocalReplacementTypography = staticCompositionLocalOf {
    ReplacementTypography(
        body = TextStyle.Default,
        title = TextStyle.Default
    )
}

@Composable
fun ReplacementTheme(
    /* ... */
    content: @Composable () -> Unit
) {
    val replacementTypography = ReplacementTypography(
        body = TextStyle(fontSize = 16.sp),
        title = TextStyle(fontSize = 32.sp)
    )
    CompositionLocalProvider(
        LocalReplacementTypography provides replacementTypography
    ) {
        MaterialTheme(
            /* colors = ..., shapes = ... */
            content = content
        )
    }
}

// Use with eg. ReplacementTheme.typography.body
object ReplacementTheme {
    val typography: ReplacementTypography
        @Composable
        get() = LocalReplacementTypography.current
}


//**********************完全自定义*****************************//
/**
 * 完全自定义颜色和排版系统
 */
@Immutable
data class CustomColors(
    val content: Color,
    val component: Color,
    val background: List<Color>
)

@Immutable
data class CustomTypography(
    val body: TextStyle,
    val title: TextStyle
)

@Immutable
data class CustomElevation(
    val default: Dp,
    val pressed: Dp
)

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        content = Color.Unspecified,
        component = Color.Unspecified,
        background = emptyList()
    )
}
val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography(
        body = TextStyle.Default,
        title = TextStyle.Default
    )
}
val LocalCustomElevation = staticCompositionLocalOf {
    CustomElevation(
        default = Dp.Unspecified,
        pressed = Dp.Unspecified
    )
}

@Composable
fun CustomTheme(
    /* ... */
    content: @Composable () -> Unit
) {
    val customColors = CustomColors(
        content = Color(0xFFDD0D3C),
        component = Color(0xFFC20029),
        background = listOf(Color.White, Color(0xFFF8BBD0))
    )
    val customTypography = CustomTypography(
        body = TextStyle(fontSize = 16.sp),
        title = TextStyle(fontSize = 32.sp)
    )
    val customElevation = CustomElevation(
        default = 4.dp,
        pressed = 8.dp
    )
    CompositionLocalProvider(
        LocalCustomColors provides customColors,
        LocalCustomTypography provides customTypography,
        LocalCustomElevation provides customElevation,
        content = content
    )
}

// Use with eg. CustomTheme.elevation.small
object CustomTheme {
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current
    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current
    val elevation: CustomElevation
        @Composable
        get() = LocalCustomElevation.current
}