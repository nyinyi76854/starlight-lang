package com.starlightlang.lib

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.view.View
import android.view.animation.*

class SLAnimation {
    var duration: Long = 300
    var delay: Long = 0
    var interpolator: TimeInterpolator = AccelerateDecelerateInterpolator()

    var alpha: Float? = null
    var scaleX: Float? = null
    var scaleY: Float? = null
    var rotation: Float? = null
    var rotationX: Float? = null
    var rotationY: Float? = null
    var translationX: Float? = null
    var translationY: Float? = null
    var pivotX: Float? = null
    var pivotY: Float? = null
    var elevation: Float? = null
    var zIndex: Float? = null

    var repeatCount: Int = 0
    var repeatMode: Int = android.animation.ValueAnimator.RESTART

    var onStart: (() -> Unit)? = null
    var onEnd: (() -> Unit)? = null

    var transformOriginX: Float? = null
    var transformOriginY: Float? = null
    var scale: Float? = null          
    var translateXBy: Float? = null   
    var translateYBy: Float? = null
    var rotateBy: Float? = null       

    fun apply(view: View) {

        transformOriginX?.let { view.pivotX = it }
        transformOriginY?.let { view.pivotY = it }

        scale?.let {
            scaleX = it
            scaleY = it
        }
        translateXBy?.let { translationX = (view.translationX + it) }
        translateYBy?.let { translationY = (view.translationY + it) }
        rotateBy?.let { rotation = (view.rotation + it) }

        val animator = view.animate()
            .setDuration(duration)
            .setStartDelay(delay)
            .setInterpolator(interpolator)

        alpha?.let { animator.alpha(it) }
        scaleX?.let { animator.scaleX(it) }
        scaleY?.let { animator.scaleY(it) }
        rotation?.let { animator.rotation(it) }
        rotationX?.let { animator.rotationX(it) }
        rotationY?.let { animator.rotationY(it) }
        translationX?.let { animator.translationX(it) }
        translationY?.let { animator.translationY(it) }

        if (repeatCount > 0) {
            animator.setListener(object : AnimatorListenerAdapter() {
                private var count = 0

                override fun onAnimationEnd(animation: Animator) {
                    if (count < repeatCount) {
                        count++
                        if (repeatMode == android.animation.ValueAnimator.REVERSE) {
                            view.animate().apply {
                                alpha?.let { alpha(1f) }
                                scaleX?.let { scaleX(1f) }
                                scaleY?.let { scaleY(1f) }
                                rotation?.let { rotation(0f) }
                                rotationX?.let { rotationX(0f) }
                                rotationY?.let { rotationY(0f) }
                                translationX?.let { translationX(0f) }
                                translationY?.let { translationY(0f) }
                            }.setDuration(duration).start()
                        }
                        view.postDelayed({ apply(view) }, delay)
                    } else {
                        onEnd?.invoke()
                    }
                }

                override fun onAnimationStart(animation: Animator) {
                    onStart?.invoke()
                }
            })
        } else {
            animator.setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    onStart?.invoke()
                }

                override fun onAnimationEnd(animation: Animator) {
                    onEnd?.invoke()
                }
            })
        }

        animator.start()
    }
}

fun View.slAnimate(block: SLAnimation.() -> Unit) {
    val animation = SLAnimation().apply(block)
    animation.apply(this)
}

fun View.fadeIn(duration: Long = 300) {
    alpha = 0f
    visibility = View.VISIBLE
    slAnimate {
        this.duration = duration
        alpha = 1f
    }
}

fun View.fadeOut(duration: Long = 300) {
    slAnimate {
        this.duration = duration
        alpha = 0f
        onEnd = { visibility = View.GONE }
    }
}

fun View.scaleIn(duration: Long = 300) {
    scaleX = 0f
    scaleY = 0f
    visibility = View.VISIBLE
    slAnimate {
        this.duration = duration
        scaleX = 1f
        scaleY = 1f
    }
}

fun View.scaleOut(duration: Long = 300) {
    slAnimate {
        this.duration = duration
        scaleX = 0f
        scaleY = 0f
        onEnd = { visibility = View.GONE }
    }
}

fun View.slideUp(distance: Float = 200f, duration: Long = 300) {
    translationY = distance
    visibility = View.VISIBLE
    slAnimate {
        this.duration = duration
        translationY = 0f
    }
}

fun View.slideDown(distance: Float = 200f, duration: Long = 300) {
    slAnimate {
        this.duration = duration
        translationY = distance
    }
}

fun View.rotate(degrees: Float = 360f, duration: Long = 500) {
    slAnimate {
        this.duration = duration
        rotation = degrees
    }
}

fun View.bounce(duration: Long = 600) {
    slAnimate {
        this.duration = duration
        translationY = -50f
        interpolator = BounceInterpolator()
        repeatCount = 1
        repeatMode = android.animation.ValueAnimator.REVERSE
    }
}

fun View.pulse(duration: Long = 500) {
    slAnimate {
        this.duration = duration
        scaleX = 1.2f
        scaleY = 1.2f
        repeatCount = 1
        repeatMode = android.animation.ValueAnimator.REVERSE
    }
}

fun View.shake(duration: Long = 500) {
    slAnimate {
        this.duration = duration
        translationX = 25f
        interpolator = CycleInterpolator(5f)
    }
}

fun View.fadeScaleIn(duration: Long = 300) {
    alpha = 0f
    scaleX = 0.8f
    scaleY = 0.8f
    visibility = View.VISIBLE
    slAnimate {
        this.duration = duration
        alpha = 1f
        scaleX = 1f
        scaleY = 1f
    }
}

fun View.fadeSlideIn(distance: Float = 100f, duration: Long = 400) {
    alpha = 0f
    translationY = distance
    visibility = View.VISIBLE
    slAnimate {
        this.duration = duration
        alpha = 1f
        translationY = 0f
    }
}

fun View.fadeSlideOut(distance: Float = 100f, duration: Long = 400) {
    slAnimate {
        this.duration = duration
        alpha = 0f
        translationY = distance
        onEnd = { visibility = View.GONE }
    }
}

fun View.rotateScale(duration: Long = 500, degrees: Float = 180f, scale: Float = 1.5f) {
    slAnimate {
        this.duration = duration
        rotation = degrees
        scaleX = scale
        scaleY = scale
    }
}

fun View.pulseFade(duration: Long = 500) {
    alpha = 0.5f
    slAnimate {
        this.duration = duration
        alpha = 1f
        scaleX = 1.2f
        scaleY = 1.2f
        repeatCount = 1
        repeatMode = android.animation.ValueAnimator.REVERSE
    }
}

fun View.bounceFade(duration: Long = 600) {
    alpha = 0.5f
    slAnimate {
        this.duration = duration
        translationY = -50f
        alpha = 1f
        interpolator = BounceInterpolator()
        repeatCount = 1
        repeatMode = android.animation.ValueAnimator.REVERSE
    }
}