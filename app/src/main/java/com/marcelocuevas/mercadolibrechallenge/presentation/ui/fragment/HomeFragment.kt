package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.navigation.fragment.findNavController
import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: GenericFragment() {

    override fun layoutRes() = R.layout.fragment_home

    override fun init() {
        setupNav(toolbar)
        setupSearchView()
    }

    private fun setupSearchView() {
        searchView.isFocusable = false
        searchViewContainer.setOnClickListener {
            findNavController().navigate(R.id.search_fragment)
        }
    }
}