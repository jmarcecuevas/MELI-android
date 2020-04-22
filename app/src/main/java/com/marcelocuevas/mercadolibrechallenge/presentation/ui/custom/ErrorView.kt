package com.marcelocuevas.mercadolibrechallenge.presentation.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.include_network_error.view.*

class ErrorView: FrameLayout {

    lateinit var onClick: () -> Unit

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.include_network_error, this)
        retryButton.setOnClickListener {
            onClick()
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
