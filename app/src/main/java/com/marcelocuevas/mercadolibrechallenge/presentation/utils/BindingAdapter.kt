package com.marcelocuevas.mercadolibrechallenge.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("hideIfFalse")
fun hideIfFalse(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("hideAlways")
fun hideAlways(view: View, value: Boolean) {
    view.visibility = View.GONE
}

@BindingAdapter("hideIfZero")
fun hideIfZero(view: View, number: Int) {
    view.visibility = if (number == 0) View.GONE else View.VISIBLE
}

@BindingAdapter("hideIfZero")
fun hideIfZero(view: View, number: Float) {
    view.visibility = if (number == 0f) View.GONE else View.VISIBLE
}