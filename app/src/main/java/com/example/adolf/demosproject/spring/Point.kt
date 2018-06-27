package com.example.adolf.demosproject.spring

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.widget.RelativeLayout

class Point :View{
    var mPaint: Paint = Paint()
    var mTouchY :Float = 0f
    var mTouchX :Float = 0f
    lateinit var mVelocityTracker: VelocityTracker

    constructor(context : Context) : super(context,null)
    constructor(context: Context,attributeSet: AttributeSet) :super(context,attributeSet){
        with(mPaint){
            color = Color.RED
            isAntiAlias = true
            style = Paint.Style.FILL
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var action = event.action
        val touchY = event.y
        val touchX = event.x
        Log.i("TTTT","$translationX ---$translationY")
        when(action){
            MotionEvent.ACTION_DOWN ->{
                mTouchX = touchX
                mTouchY = touchY
            mVelocityTracker = VelocityTracker.obtain()
            }
            MotionEvent.ACTION_MOVE ->{
                translationX += (touchX - mTouchX)
                translationY += (touchY - mTouchY)
                mVelocityTracker.addMovement(event)
                mVelocityTracker.computeCurrentVelocity(1000)
            }
            MotionEvent.ACTION_UP ->{
                var springAnimationX :SpringAnimation = SpringAnimation(this,DynamicAnimation.TRANSLATION_X,0f)
                var springAnimationY :SpringAnimation = SpringAnimation(this,DynamicAnimation.TRANSLATION_Y,0f)
                val velocityX = mVelocityTracker.xVelocity
                val velocityY = mVelocityTracker.yVelocity
                springAnimationX.setStartVelocity(velocityX)
                springAnimationY.setStartVelocity(velocityY)
                springAnimationX.start()
                springAnimationY.start()
                springAnimationX.spring.dampingRatio = 0.5f
                springAnimationY.spring.dampingRatio = 0.5f
                springAnimationX.spring.stiffness = 500f
                springAnimationY.spring.stiffness = 500f

            }
        }

        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(140,140)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(RADIUS,RADIUS,RADIUS,mPaint)
    }

    companion object {
        const val RADIUS = 70f
    }
}