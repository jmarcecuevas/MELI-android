package com.marcelocuevas.mercadolibrechallenge.presentation.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.core.widget.ContentLoadingProgressBar
import com.bumptech.glide.Glide

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.visibleOrGone(visible: Boolean) {
    val map: Map<Boolean, Int> = mapOf(true to VISIBLE, false to GONE)
    map[visible]?.let { this.visibility = it }
}

fun ImageView.loadImage(url: String) = Glide.with(this).load(url).into(this)

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ContentLoadingProgressBar.shouldShow(value: Boolean) {
    if (value)
        show()
    else
        hide()
}
