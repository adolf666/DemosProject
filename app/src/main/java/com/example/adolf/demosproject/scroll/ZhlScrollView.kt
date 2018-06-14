package com.example.adolf.demosproject.scroll

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.widget.LinearLayout
import android.widget.Scroller

class ZhlScrollView :LinearLayout{
    constructor(context: Context) : super(context)
    constructor(context: Context,attributeSet: AttributeSet) : super(context,attributeSet)
    constructor(context: Context,attributeSet: AttributeSet,defStyleAttr :Int) :super(context,attributeSet,defStyleAttr)
    private val mScroller = Scroller(context)
    private var mActionDownY :Int = 0
    private lateinit var mVelocityTracker :VelocityTracker
    var mPointId :Int = 0
    var mVelocityY :Float = 0.0f

    init {
        orientation = VERTICAL
        isVerticalScrollBarEnabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchY = event.y.toInt()
        val action = event.action
        mScroller.abortAnimation()
        when(action){
            MotionEvent.ACTION_DOWN -> handleActionDown(event,touchY)
            MotionEvent.ACTION_MOVE -> handleActionMove(event,touchY)
            MotionEvent.ACTION_UP -> handleActionUp()
        }
        return true
    }

    private fun handleActionDown(event: MotionEvent,touchY :Int){
        mActionDownY = touchY
        mVelocityTracker = VelocityTracker.obtain()
        mPointId = event.getPointerId(0)
    }

    private fun handleActionMove(event: MotionEvent,touchY: Int){
        var dy = mActionDownY - touchY
        Log.i("TTTT", dy.toString())
        if (scrollY < 0) {
            if (dy >= -2) dy = 0 else dy = -1
        }
        if (scrollY > getAllChildrenHeight() - measuredHeight){
            if (dy <= 2) dy = 0 else dy = 1
        }
        scrollBy(0,dy)
        mActionDownY = touchY
        mVelocityTracker.addMovement(event)
        mVelocityTracker.computeCurrentVelocity(1000)
        mVelocityY = mVelocityTracker.getYVelocity(mPointId)

    }

    private fun handleActionUp(){

        if (scrollY < 0) {
            smoothScrollTo(0, 0)
        } else if (scrollY > getAllChildrenHeight() - measuredHeight) {
            smoothScrollTo(0, getAllChildrenHeight() - measuredHeight)
        }else{
            mScroller.fling(0,scrollY,0,-mVelocityY.toInt(),0,0,100,1000)
        }
    }

    private fun smoothScrollTo(destX :Int,destY :Int){
        val scrollX = scrollX
        val scrollY = scrollY
        val deltaX = destX - scrollX
        val deltaY = destY - scrollY
        mScroller.startScroll(scrollX,scrollY,deltaX,deltaY,500)
        invalidate()
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.currX,mScroller.currY)
        }else{
            if (scrollY >= getAllChildrenHeight() - measuredHeight){
                mScroller.abortAnimation()
                smoothScrollTo(0, getAllChildrenHeight() - measuredHeight)
            }
        }
        postInvalidate()
    }

    private fun getAllChildrenHeight():Int{
        var height = 0
        for (i in 0 until childCount){
            height += getChildAt(i).measuredHeight
        }
        return height
    }
}