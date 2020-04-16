package com.marcelocuevas.mercadolibrechallenge.ui.fragment

import androidx.appcompat.widget.SearchView
import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment: GenericFragment() {

    override fun layoutRes() = R.layout.fragment_search

    override fun init() {
        setupNav(toolbar)
        setupSearchView()
    }

    private fun setupSearchView() {
        searchView.onActionViewExpanded()
        searchView.isIconified = false

        setupSearchViewListener()
    }

    private fun setupSearchViewListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                navigateTo(R.id.product_list_fragment)
                return true
            }
        })
    }
}