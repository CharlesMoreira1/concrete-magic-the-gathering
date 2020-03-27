package com.concrete.magicthegathering.core.util

import android.animation.ObjectAnimator
import android.view.View

fun View.rotationAnimation(): View{
    ObjectAnimator
        .ofFloat(this, View.ROTATION, 0f, 360f)
        .setDuration(300).start()
    return this
}