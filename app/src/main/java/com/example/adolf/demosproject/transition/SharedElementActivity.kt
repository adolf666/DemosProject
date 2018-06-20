package com.example.adolf.demosproject.transition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.transition.Transition
import android.transition.TransitionInflater
import com.example.adolf.demosproject.R
import kotlinx.android.synthetic.main.activity_shared_element.*

class SharedElementActivity : AppCompatActivity() {
    lateinit var mEnterTransition :Transition
    lateinit var mSharedElementEnterTransition :Transition
    lateinit var mReturnTransition: Transition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element)
        initEnterTransition()
        initSharedElementEnterTransition()
        initReturnTransition()
    }

    private fun initEnterTransition(){
        mEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide_fade_transition)
        window.enterTransition = mEnterTransition
    }

    private fun initSharedElementEnterTransition(){
        mSharedElementEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.changebounds_with_arcmotion)
        mSharedElementEnterTransition.addListener(object :Transition.TransitionListener{
            override fun onTransitionEnd(transition: Transition?) {
                ViewCompat.animate(mHeadIv).alpha(1.0f).setDuration(600).start()
                ViewCompat.animate(fadeIv).alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(400).start()
            }

            override fun onTransitionResume(transition: Transition?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionPause(transition: Transition?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionCancel(transition: Transition?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionStart(transition: Transition?) {

            }

        })
        window.sharedElementEnterTransition = mSharedElementEnterTransition
    }

    private fun initReturnTransition(){
        mReturnTransition = TransitionInflater.from(this).inflateTransition(R.transition.return_transition)
        window.returnTransition = mReturnTransition
    }

    override fun onBackPressed() {
        if (!slideLayout.isTransitionGroup) slideLayout.isTransitionGroup = true
        super.onBackPressed()
        finishAfterTransition()
    }
}
