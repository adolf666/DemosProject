package com.example.adolf.demosproject.transition

import android.animation.Animator
import android.content.Context
import android.support.transition.Transition
import android.support.transition.TransitionValues
import android.util.AttributeSet
import android.view.ViewGroup

class CustomerTransition : Transition {

    constructor() :super()
    constructor(context:Context,attr : AttributeSet) :super(context,attr)

    override fun captureStartValues(p0: TransitionValues) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun captureEndValues(p0: TransitionValues) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
        return super.createAnimator(sceneRoot, startValues, endValues)
    }
}