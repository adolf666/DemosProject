package com.example.adolf.demosproject.transition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import com.example.adolf.demosproject.R
import kotlinx.android.synthetic.main.activity_layout_transition.*

class LayoutTransitionActivity : AppCompatActivity() {
    private lateinit var mScene :Scene
    private lateinit var mTransition: Transition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_transition)
        init()
    }

    private fun init(){
        mScene = Scene.getSceneForLayout(mSceneRootLayout,R.layout.item_layout_transition,this)
        mTransition = TransitionInflater.from(this).inflateTransition(R.transition.layout_anim)
        with(mTransition){
            this.duration = 500
        }
        TransitionManager.go(mScene,mTransition)
    }
}
