package com.example.adolf.demosproject.transition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import com.example.adolf.demosproject.R

class SharedElementActivity : AppCompatActivity() {
    lateinit var mEnterTransition :Transition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_element)
    }

    private fun init(){
        mEnterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide_fade_transition)
        window.enterTransition = mEnterTransition
    }
}
