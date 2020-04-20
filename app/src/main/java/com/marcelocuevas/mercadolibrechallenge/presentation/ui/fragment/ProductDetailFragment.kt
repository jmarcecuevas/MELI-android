package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.fragment_product_detail.*


class ProductDetailFragment: GenericFragment() {

    override fun layoutRes() = R.layout.fragment_product_detail

    override fun init() {
        setupNav(toolbar)
    }
}