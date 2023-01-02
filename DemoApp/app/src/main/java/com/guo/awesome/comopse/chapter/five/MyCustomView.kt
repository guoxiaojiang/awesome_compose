package com.guo.awesome.comopse.chapter.five

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class MyCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //自定义图形绘制
    }
}