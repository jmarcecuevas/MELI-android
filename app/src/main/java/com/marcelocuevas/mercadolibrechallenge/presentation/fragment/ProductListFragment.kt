package com.marcelocuevas.mercadolibrechallenge.presentation.fragment

import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment: GenericFragment() {

    override fun layoutRes() = R.layout.fragment_product_list

    override fun init() {
        setupNav(toolbar)
    }
}