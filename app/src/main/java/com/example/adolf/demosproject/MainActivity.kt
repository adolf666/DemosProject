package com.example.adolf.demosproject

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.adolf.demosproject.transition.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMyScrollView.setOnClickListener { goToScrollViewActivity() }
        mActivityAnim.setOnClickListener { goToActivityAnim() }
        mAnimExplode.setOnClickListener { goToActivityExplodeAnim() }
        mAnimLayout.setOnClickListener { goToLayoutAnim() }
        mDelayTransition.setOnClickListener { goToDelayTransitionActivity() }
        mClipBoundTransition.setOnClickListener { goToClipBoundActivity() }

    }

    private fun goToScrollViewActivity(){
        startActivity<ScrollViewActivity>()
    }

    private fun goToActivityAnim(){
        val intent = Intent(this,TransitionBottomActivity::class.java)
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }
    private fun goToActivityExplodeAnim(){
        val intent = Intent(this,TransitionExplodeActivity::class.java)
        startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

    private fun goToLayoutAnim(){
        startActivity<LayoutTransitionActivity>()
    }

    private fun goToDelayTransitionActivity(){
        startActivity<TransitionDelayActivity>()
    }

    private fun goToClipBoundActivity(){
        startActivity<TransitionChangBoundsActivity>()
    }
}
