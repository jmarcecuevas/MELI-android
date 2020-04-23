package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: BaseFragment() {

    private val viewModel by viewModel<SearchViewModel>()

    override fun layoutRes() = R.layout.fragment_search

    override fun init() {
        setupNav(toolbar)

        viewModel.onStart()

        startObserving()
        setupSearchView()
        setupSearchViewListener()
    }

    private fun startObserving() {
        viewModel.searches.observe(this, Observer {

        })
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
                viewModel.searchButtonClicked(query)
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