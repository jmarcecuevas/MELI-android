package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.ProductsListAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.shouldShow
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search_results.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultsFragment: GenericFragment() {

    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = ProductsListAdapter()

    private val queryText: String
        get() = SearchResultsFragmentArgs.
            fromBundle(requireArguments()).queryText

    override fun layoutRes() = R.layout.fragment_search_results

    override fun init() {
        setupNav(toolbar)

        resultsRecyclerView.layoutManager = LinearLayoutManager(context)
        resultsRecyclerView.adapter = adapter

        viewModel.search(queryText)

        startObserving()
        viewModel.productsLiveData.observe(this, Observer {

        })
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
}