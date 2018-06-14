package com.example.adolf.demosproject.transition

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.View
import com.example.adolf.demosproject.R
import kotlinx.android.synthetic.main.activity_transition_delay.*

class TransitionDelayActivity : AppCompatActivity() {
    private lateinit var mTransition :Transition
    private var mIsImageBigger :Boolean = true
    private var mWidth = 0
    private var mHeight = 0
    private var mViewList = mutableListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_delay)
        init()
    }

    private fun init(){
        mTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode_and_changebounds)
        mViewList.add(mTopRightIV)
        mViewList.add(mTopLeftIV)
        mViewList.add(mLeftBottomIv)
        mViewList.add(mRightBottomIv)
        for (i in 0 until mViewList.size){
            mViewList[i].setOnClickListener { handleImageClick(i) }
        }
        mWidth = mTopLeftIV.layoutParams.width
        mHeight = mTopLeftIV.layoutParams.height
    }

    private fun handleImageClick(index :Int){
        initTransition()
        changeSize(index)
    }

    private fun changeSize(index :Int){
        var layoutParams = mViewList[index].layoutParams
        layoutParams.width = if (mIsImageBigger) (mWidth *1.5).toInt() else mWidth
        layoutParams.height = (if (mIsImageBigger) (mHeight * 1.5).toInt() else mHeight)
        mViewList[index].layoutParams = layoutParams
        setVisible(index)
        mIsImageBigger = !mIsImageBigger
    }

    private fun setVisible(index :Int){
        for (i in 0 until mViewList.size){
            if (i == index){
                mViewList[i].visibility = View.VISIBLE
            }else {
                mViewList[i].visibility = if (mIsImageBigger) View.INVISIBLE else View.VISIBLE
            }
        }
    }

    private fun initTransition(){
        TransitionManager.beginDelayedTransition(mSceneRootLayout,mTransition)
    }
}
