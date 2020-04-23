package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.ItemsAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.shouldShow
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.visibleOrGone
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchResultsViewModel
import kotlinx.android.synthetic.main.fragment_search_results.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultsFragment: BaseFragment() {

    private val viewModel by viewModel<SearchResultsViewModel>()
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

        viewModel.onStart(queryText)

        errorView.onClick = {
            viewModel.retryClicked()
        }

        startObserving()
    }

    private fun setupRecyclerView() {
        resultsRecyclerView.layoutManager = LinearLayoutManager(context)
        resultsRecyclerView.adapter = adapter
    }

    private fun startObserving() {
        viewModel.items.observe(this, Observer {
            adapter.loadItems(it)
        })

        viewModel.hasError.observe(this, Observer {
            errorView.visibleOrGone(it)
            if (it) errorView.show() else errorView.hide()
        })

        viewModel.loading.observe(this, Observer {
            progressBar.shouldShow(it)
        })
    }

    private fun navigateToDetail(id: String) {
        val directions: SearchResultsFragmentDirections.ToProductDetailFragment =
            SearchResultsFragmentDirections.toProductDetailFragment(id)
        navigateTo(directions)
    }
}