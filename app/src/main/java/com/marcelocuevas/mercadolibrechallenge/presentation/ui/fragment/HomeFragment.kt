package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.navigation.fragment.findNavController
import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment: BaseFragment() {

    override fun layoutRes() = R.layout.fragment_home

    override fun init() {
        setupNav(toolbar)
        setupSearchView()
    }

    private fun setupSearchView() {
        searchView.isFocusable = false
        searchViewContainer.setOnClickListener {
            Timber.d("SearchView clicked")
            findNavController().navigate(R.id.search_fragment)
        }
    }
}