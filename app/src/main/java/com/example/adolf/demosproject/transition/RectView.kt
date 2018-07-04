package com.example.adolf.demosproject.transition

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class RectView :View{
    constructor(context :Context) :super(context)
    constructor(context :Context,attr :AttributeSet) :super(context,attr)
    var mRadius = 0f
    var mBackground = 0
    var mRectF :RectF = RectF()
    var mPaint :Paint = Paint()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}