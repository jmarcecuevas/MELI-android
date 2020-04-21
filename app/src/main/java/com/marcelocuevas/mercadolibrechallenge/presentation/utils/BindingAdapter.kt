package com.marcelocuevas.mercadolibrechallenge.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:hideIfEmpty")
fun hideIfEmpty(view: View, value: String) {
    view.visibility = if (value.isEmpty()) View.GONE else View.VISIBLE
}

@BindingAdapter("app:hideIfFalse")
fun hideIfFalse(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("app:hideIfZero")
fun hideIfZero(view: View, number: Int) {
    view.visibility = if (number == 0) View.GONE else View.VISIBLE
}

@BindingAdapter("app:hideIfZero")
fun hideIfZero(view: View, number: Float) {
    view.visibility = if (number == 0f) View.GONE else View.VISIBLE
}