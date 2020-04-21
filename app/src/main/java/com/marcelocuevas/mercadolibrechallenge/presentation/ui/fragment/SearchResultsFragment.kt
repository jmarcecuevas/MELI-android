package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.ItemsAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.shouldShow
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search_results.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultsFragment: GenericFragment() {

    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = ItemsAdapter() {
        navigateToDetail(it)
    }

    private val queryText: String
        get() = SearchResultsFragmentArgs.
            fromBundle(requireArguments()).queryText

    override fun layoutRes() = R.layout.fragment_search_results

    override fun init() {
        setupNav(toolbar)
        setupRecyclerView()

        viewModel.search(queryText)

        startObserving()
    }

    private fun setupRecyclerView() {
        resultsRecyclerView.layoutManager = LinearLayoutManager(context)
        resultsRecyclerView.adapter = adapter
    }

    private fun startObserving() {
        viewModel.productsLiveData.observe(this, Observer {
            adapter.loadItems(it)
        })

        viewModel.errorMessageLiveData.observe(this, Observer {

        })

        viewModel.isLoadingLiveData.observe(this, Observer {
            progressBar.shouldShow(it)
        })
    }

    private fun navigateToDetail(id: String) {
        val directions: SearchResultsFragmentDirections.ToProductDetailFragment =
            SearchResultsFragmentDirections.toProductDetailFragment(id)
        navigateTo(directions)
    }
}