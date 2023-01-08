package com.guo.awesome.comopse.chapter.four

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guo.awesome.comopse.chapter.R

@Composable
fun SimpleText() {
    Text("Hello World!")
}

/**
 * Android View系统中的TextView控件使用
 */
//fun displayText() {
//    val textView = TextView(this)
//    textView.text = "Hello World!"
//    val container = findViewById<LinearLayout>(R.id.ll_view_container)
//    container.addView(textView)
//}

@Composable
fun StringResourceText() {
    Text(stringResource(R.string.hello_world))
}

@Composable
fun TestTextColor() {
    Text(text = "Hello Compose!", color = Color.Green)
}

@Composable
fun TestTextSize() {
    Text(text = "Hello Compose!", fontSize = 30.sp)
}

@Composable
fun TestFontStyle() {
    Text(text = "Hello Compose!", fontStyle = FontStyle.Italic)
}

@Composable
fun TestFontFamilies() {
    Column {
        Text(text = "Hello Compose!", fontFamily = FontFamily.Default)
        Text(text = "Hello Compose!", fontFamily = FontFamily.SansSerif)
        Text(text = "Hello Compose!", fontFamily = FontFamily.Serif)
        Text(text = "Hello Compose!", fontFamily = FontFamily.Monospace)
        Text(text = "Hello Compose!", fontFamily = FontFamily.Cursive)
    }
}

val firasansFonts = FontFamily(
    Font(R.font.firasans_bold, FontWeight.Bold),
    Font(R.font.firasans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.firasans_light, FontWeight.Light),
    Font(R.font.firasans_medium, FontWeight.Medium),
    Font(R.font.firasans_regular, FontWeight.Normal),
    Font(R.font.firasans_thin, FontWeight.Thin)
)

@Composable
fun TestCustomFontFamilies() {
    Column {
        Text(
            text = "Hello Compose!",
            fontFamily = firasansFonts,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Hello Compose!",
            fontFamily = firasansFonts,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "Hello Compose!",
            fontFamily = firasansFonts,
            fontWeight = FontWeight.Light
        )
        Text(
            text = "Hello Compose!",
            fontFamily = firasansFonts,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "Hello Compose!",
            fontFamily = firasansFonts,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "Hello Compose!",
            fontFamily = firasansFonts,
            fontWeight = FontWeight.Thin
        )
    }
}

@Composable
fun TestLetterSpace() {
    Text(text = "Hello Compose!", letterSpacing = 4.sp)
}

@Composable
fun TestTextDecoration() {
    Column {
        Text(text = "Hello Compose!", textDecoration = TextDecoration.None)
        Text(text = "Hello Compose!", textDecoration = TextDecoration.Underline)
        Text(text = "Hello Compose!", textDecoration = TextDecoration.LineThrough)
    }
}

@Composable
fun TestLineHeight() {
    Text(
        text = "Jetpack Compose 是用于构建原生 Android 界面的新工具包",
        lineHeight = 30.sp
    )
}

@Composable
fun TestOverflowedText() {
    Text("Hello Compose ".repeat(50), maxLines = 2, overflow = TextOverflow.Clip)
    Text("Hello Compose ".repeat(50), maxLines = 2, overflow = TextOverflow.Ellipsis)
    Text("Hello Compose ".repeat(50), maxLines = 2, overflow = TextOverflow.Visible)
}

@Composable
fun TestTextLines() {
    Text(text = "Hello Compose! ".repeat(30), maxLines = 2)
}

@Composable
fun TestTextMultipleStyles() {
    Column {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Green)) {
                    append("H")
                }
                append("ello ")

                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                ) {
                    append("C")
                }
                append("ompose")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = ParagraphStyle(textAlign = TextAlign.Start)) {
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append("Hello\n")
                    }
                    withStyle(
                        style = SpanStyle(color = Color.Green, fontSize = 30.sp)
                    ) {
                        append("Compose ")
                    }
                    append("World")
                }
            }
        )
    }
}

@Composable
fun TestSelectableText() {
    SelectionContainer {
        Text("这些文字可以被选择")
    }
}

@Composable
fun TestPartiallySelectableText() {
    SelectionContainer {
        Column {
            Text(text = "这段文字可以被选择")
            Text(text = "这段也可以")
            DisableSelection {
                Text(text = "这段文字不可以被选择")
            }
            Text(text = "这段又可以被选择")
        }
    }
}

@Composable
fun TestClickText() {
    Text(text = "文字响应点击事件", modifier = Modifier.clickable {
        // 响应 Click 事件
    })
}

@Composable
fun TestPartiallyClickText() {
    val annotatedString = buildAnnotatedString {
        append("点击进入")
        // 将 tag="URL" 的注解附加到接下来一直到 pop() 方法被调用的范围内的文字上
        pushStringAnnotation(
            tag = "URL",
            annotation = "https://developer.android.com/jetpack/compose/"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        ) {
            append("Compose官网")
        }
        pop()
    }
    ClickableText(text = annotatedString, onClick = { offset ->
        // 检查是否有 tag="URL" 的注解附加在点击的文字上
        annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
            .firstOrNull()?.let { annotation ->
                // 响应 Click 事件
                Log.d("Clicked URL", annotation.item)
            }
    })
}

@Composable
fun TestFieldAndOutlinedText() {
    var text by remember { mutableStateOf("Hello") }
    Column {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("TextField") }
        )
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(text = "OutlinedTextField") })
    }
}

@Composable
fun TestTextFieldStyle() {
    var value by remember { mutableStateOf("Hello\nCompose\nInvisible") }
    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Enter text") },
        maxLines = 2,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun TestImeAction() {
    val text = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    OutlinedTextField(
        value = text.value, onValueChange = { text.value = it },
        label = { Text(text = "Edit phone number") },
        modifier = Modifier.padding(10.dp),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Characters,
            keyboardType = KeyboardType.Phone,
            autoCorrect = true,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                Toast.makeText(context, "Tel: ${text.value}", Toast.LENGTH_LONG).show()
            }
        )
    )
}

@Composable
fun TestPasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Enter password") },
        modifier = Modifier.padding(10.dp),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}


/*********Compose 按钮控件************/
@Composable
fun TestButton() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "这是一个按钮")
    }
}

@Composable
fun TestButton1() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = "这是一个按钮")
    }
}

@Composable
fun MyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        onClick = onClick,
        modifier = modifier,
        content = content
    )
}


/*********Compose 图片控件**************/
@Composable
fun TestImage() {
    Image(
        painter = painterResource(id = R.drawable.scenary),
        contentDescription = "This is an Image"
    )
}

@Composable
fun TestImageAlpha() {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "文字内容")
        Image(
            painter = painterResource(id = R.drawable.scenary),
            contentDescription = "This is an Image",
            alpha = 0.8f //设置图片的不透明度
        )
    }
}

//SubcomposeAsyncImage(
//    model = "https://example.com/image.jpg",
//    contentDescription = stringResource(R.string.description)
//) {
//    val state = painter.state
//    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
//        CircularProgressIndicator()
//    } else {
//        SubcomposeAsyncImageContent()
//    }
//}


/*********Compose 布局组件*********/
@Composable
fun ArtistCard() {
    Column {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Composable
fun TestModifier() {
    val context = LocalContext.current
    val content = "你好！"
    Row(modifier = Modifier.clickable {
        Toast
            .makeText(
                context,
                content,
                Toast.LENGTH_SHORT
            )
            .show()
    }) {
        Text(text = content)
    }
}


@Composable
fun Image(imageUrl: String) {
    //TODO
}

@Composable
fun Title(title: String) {
    //TODO
}

@Composable
fun Description(desc: String) {
    //TODO
}

@Composable
fun Description(description: String, showMore: Boolean, onShowMoreToggled: (Boolean) -> Unit) {
    //TODO
}

@Composable
fun Card(
    imageUrl: String,
    title: String,
    description: String
) {
    BoxWithConstraints {
        if (maxWidth < 400.dp) {
            Column {
                Image(imageUrl)
                Title(title)
            }
        } else {
            Row {
                Column {
                    Title(title)
                    Description(description)
                }
                Image(imageUrl)
            }
        }
    }
}

@Composable
fun Card1(
    imageUrl: String,
    title: String,
    description: String
) {
    var showMore by remember { mutableStateOf(false) }

    BoxWithConstraints {
        if (maxWidth < 400.dp) {
            Column {
                Image(imageUrl)
                Title(title)
            }
        } else {
            Row {
                Column {
                    Title(title)
                    Description(
                        description = description,
                        showMore = showMore,
                        onShowMoreToggled = { newValue ->
                            showMore = newValue
                        }
                    )
                }
                Image(imageUrl)
            }
        }
    }
}


//@ExperimentalComposeUiApi
//@Composable
//fun TestConstraintLayout() {
//    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//// 创建3个引用
//        val (text, buttonLeft, buttonRight) = createRefs()
//
//        // 将引用 text 与可组合项 Text 关联
//        Text("This is the content of Text", Modifier.constrainAs(text) {
//            //将Text可组合项约束到父项的中心位置
//            centerTo(parent)
//        })
//
//        Button(
//            onClick = { /* Do something */ },
//            // 将引用 buttonLeft 与可组合项 Button 关联
//            modifier = Modifier.constrainAs(buttonLeft) {
//                // 将 buttonLeft 约束到顶部与 text 底部对齐，并保持16dp的距离
//                top.linkTo(text.bottom, margin = 16.dp)
//                // 将 buttonLeft 约束到相对于 text 的 start 边界水平方向轴对称
//                centerAround(text.start)
//            }
//        ) {
//            Text("Left Button")
//        }
//
//        Button(
//            onClick = { /* Do something */ },
//            // 将引用 buttonRight 与可组合项 Button 关联
//            modifier = Modifier.constrainAs(buttonRight) {
//                // 将 buttonRight 的顶部约束到与 buttonLeft 顶部对齐
//                top.linkTo(buttonLeft.top)
//                // 将 buttonRight 约束到相对于 text 的 end 边界水平方向轴对称
//                centerAround(text.end)
//            }
//        ) {
//            Text("Right Button")
//        }
//    }
//}