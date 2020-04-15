package com.marcelocuevas.mercadolibrechallenge.ui.fragment

import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: GenericFragment() {

    override fun layoutRes() = R.layout.fragment_home

    override fun init() {
        setupNav(toolbar)

        searchViewContainer.setOnClickListener {
           navigateTo(R.id.search_fragment)
        }
    }
}