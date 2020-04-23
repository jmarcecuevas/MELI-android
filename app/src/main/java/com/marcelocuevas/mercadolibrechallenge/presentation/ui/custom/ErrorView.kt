package com.marcelocuevas.mercadolibrechallenge.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.view_network_error.view.*

class ErrorView: FrameLayout {

    var onClick: (() -> Unit)? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_network_error, this)
        retryButton.setOnClickListener {
            onClick?.let { it() }
        }
    }

    fun show() {

        if (!animationView.isAnimating) {
            animationView.playAnimation()
        }
    }

    fun hide() {
        if (animationView.isAnimating) {
            animationView.pauseAnimation()
        }
    }
}
