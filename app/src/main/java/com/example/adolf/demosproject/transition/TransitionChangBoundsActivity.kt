package com.example.adolf.demosproject.transition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import com.example.adolf.demosproject.R
import kotlinx.android.synthetic.main.activity_transition_chang_bounds.*

class TransitionChangBoundsActivity : AppCompatActivity() {
    private lateinit var mScene1 :Scene
    private lateinit var mScene2 :Scene
    protected var isScene2: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_chang_bounds)
        mScene1 = Scene.getSceneForLayout(mSceneRootLayout,R.layout.item_scene1,this)
        mScene2 = Scene.getSceneForLayout(mSceneRootLayout,R.layout.item_scene2,this)
        changeScene()
        mChangeBtn.setOnClickListener { changeScene() }
    }

    private fun changeScene(){
        TransitionManager.go(if (isScene2) mScene2 else mScene1 ,ChangeBounds())
        isScene2 = !isScene2
    }
}
