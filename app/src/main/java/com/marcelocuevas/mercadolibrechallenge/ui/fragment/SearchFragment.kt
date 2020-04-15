package com.marcelocuevas.mercadolibrechallenge.ui.fragment

import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment: GenericFragment() {

    override fun layoutRes() = R.layout.fragment_search

    override fun init() {
        setupNav(toolbar)


    }
}