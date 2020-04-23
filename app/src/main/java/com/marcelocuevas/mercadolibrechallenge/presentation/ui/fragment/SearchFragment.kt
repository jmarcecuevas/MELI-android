package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.RecordsAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: BaseFragment() {

    private val viewModel by viewModel<SearchViewModel>()

    private lateinit var adapter: RecordsAdapter

    override fun layoutRes() = R.layout.fragment_search

    override fun init() {
        setupNav(toolbar)

        viewModel.onStart()

        setupRecyclerView()

        startObserving()

        setupSearchView()

        setupSearchViewListener()
    }

    private fun setupRecyclerView() {
        recordsRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecordsAdapter {
            navigateToSearchResults(it)
        }
        recordsRecyclerView.adapter = adapter
    }

    private fun startObserving() {
        viewModel.searches.observe(this, Observer {
            adapter.loadItems(it)
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