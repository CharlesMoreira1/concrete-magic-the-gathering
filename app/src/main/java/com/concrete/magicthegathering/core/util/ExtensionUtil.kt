package com.concrete.magicthegathering.core.util

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

fun View.fadeAnimation(){
    this.alpha = 0.3f
    this.animate().setDuration(400)
        .setInterpolator(AccelerateDecelerateInterpolator())
        .alpha(1f)
}

fun View.rotationAnimation(): View{
    ObjectAnimator
        .ofFloat(this, View.ROTATION, 0f, 360f)
        .setDuration(300).start()
    return this
}