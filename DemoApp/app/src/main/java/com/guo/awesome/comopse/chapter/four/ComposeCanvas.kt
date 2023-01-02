package com.guo.awesome.comopse.chapter.four

import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.guo.awesome.comopse.chapter.R

//@Preview(showBackground = true)
@Composable
fun TestDrawPoints() {
    val points = arrayListOf(
        Offset(50f, 60f),
        Offset(100f, 90f),
        Offset(120f, 120f),
        Offset(150f, 160f),
        Offset(180f, 180f),
        Offset(200f, 210f),
        Offset(240f, 250f)
    )
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawPoints(
            points,
            pointMode = PointMode.Lines,
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round
        )
    })
}

//@Preview(showBackground = true)
@Composable
fun TestDrawLine() {
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawLine(
            color = Color.Blue,
            start = Offset(50f, 30f),
            end = Offset(210f, 220f),
            strokeWidth = 20f,
            cap = StrokeCap.Round
        )
    })
}

//@Preview(showBackground = true)
@Composable
fun TestDrawRect() {
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawRect(
            color = Color.Red,
            topLeft = Offset(50f, 60f),
            size = Size(200f, 120f),
            style = Stroke(width = 6f, miter = 6f, cap = StrokeCap.Butt, join = StrokeJoin.Round)
        )
    })
}

//@Preview(showBackground = true)
@Composable
fun TestDrawRoundRect() {
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawRoundRect(
            color = Color.Red,
            topLeft = Offset(50f, 60f),
            size = Size(200f, 120f),
            cornerRadius = CornerRadius(30f),
            style = Stroke(
                width = 2f, miter = 2f, cap = StrokeCap.Round,
                join = StrokeJoin.Miter
            )
        )
    })
}

//@Preview(showBackground = true)
@Composable
fun TestDrawCircle() {
    Canvas(modifier = Modifier.size(200.dp), onDraw = {
        drawCircle(
            color = Color.Blue,
            radius = 180f,
            center = center,
            style = Stroke(width = 10f)
        )
    })
}


//@Preview(showBackground = true)
@Composable
fun TestDrawOval() {
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawOval(
            color = Color.Blue,
            topLeft = Offset(10f, 10f),
            size = Size(90.dp.toPx(), 60.dp.toPx()),
            style = Stroke(width = 2f)
        )
    })
}


@Composable
fun TestDrawArc() {
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawArc(
            color = Color.Blue,
            startAngle = 20f,
            sweepAngle = 73f,
            useCenter = true,
            topLeft = Offset(10f, 10f),
            size = Size(90.dp.toPx(), 60.dp.toPx()),
            style = Stroke(width = 2f)
        )
//        drawOval(
//            color = Color.Green,
//            topLeft = Offset(10f, 10f),
//            size = Size(90.dp.toPx(), 60.dp.toPx()),
//            style = Stroke(width = 1f)
//        )
    })
}


@Composable
fun TestDrawImage() {
    val context = LocalContext.current
    val bmp = BitmapFactory.decodeResource(context.resources, R.drawable.scenary)
    val imageBmp = bmp.asImageBitmap()
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawImage(
            image = imageBmp,
            srcOffset = IntOffset(imageBmp.width / 4, 20.dp.roundToPx()),
            srcSize = IntSize(imageBmp.width, imageBmp.height / 2),
            dstOffset = IntOffset(20, 80),
            dstSize = IntSize(480.dp.roundToPx(), 280.dp.roundToPx())
        )
    }
}


@Composable
fun TestDrawPath() {
    val path = Path()
    path.moveTo(30f, 30f)
    path.lineTo(110f, 20f)
    val rect = Rect(130f, 30f, 250f, 100f)
    path.arcTo(rect, 0f, 150f, false)
    path.relativeLineTo(-10f, 20f)
    path.quadraticBezierTo(10f, 100f, 100f, 220f)
    path.close()
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawPath(path, color = Color.Red, style = Stroke(width = 4f))
    })
}


@Preview(showBackground = true)
@Composable
fun TestBlendMode() {
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawCircle(
            Color.Red,
            radius = 80f,
            center = Offset(110f, 110f),
            blendMode = BlendMode.SrcIn
        )
        drawRect(
            color = Color.Blue,
            topLeft = Offset(110f, 110f),
            size = Size(130f, 130f),
            blendMode = BlendMode.Difference
        )
    })
}