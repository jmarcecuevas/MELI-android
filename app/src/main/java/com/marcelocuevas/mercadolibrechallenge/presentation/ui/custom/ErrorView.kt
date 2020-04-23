package com.marcelocuevas.mercadolibrechallenge.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.view_network_error.view.*
import timber.log.Timber

class ErrorView: FrameLayout {

    var onClick: (() -> Unit)? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_network_error, this)
        retryButton.setOnClickListener {
            Timber.d("Retry button clicked")
            onClick?.let { it() }
        }
    }

    fun show() {
        if (!animationView.isAnimating) {
            Timber.d("Playing lottie animation")
            animationView.playAnimation()
        }
    }

    fun hide() {
        if (animationView.isAnimating) {
            Timber.d("Pausing lottie animation")
            animationView.pauseAnimation()
        }
    }
}
