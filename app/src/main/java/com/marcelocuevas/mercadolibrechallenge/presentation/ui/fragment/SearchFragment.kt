package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.appcompat.widget.SearchView
import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment: GenericFragment() {

    override fun layoutRes() = R.layout.fragment_search

    override fun init() {
        setupNav(toolbar)

        setupSearchView()
        setupSearchViewListener()
    }

    private fun setupSearchView() {
        searchView.onActionViewExpanded()
        searchView.isIconified = false
    }

    private fun setupSearchViewListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                navigateToSearchResults(query)
                return true
            }
        })
    }

    private fun navigateToSearchResults(query: String) {
        val directions: SearchFragmentDirections.ToSearchResultsFragment =
            SearchFragmentDirections.toSearchResultsFragment(query)
        navigateTo(directions)
    }
}