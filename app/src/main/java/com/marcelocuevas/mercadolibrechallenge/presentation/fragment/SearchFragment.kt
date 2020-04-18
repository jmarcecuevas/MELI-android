package com.marcelocuevas.mercadolibrechallenge.presentation.fragment

import androidx.appcompat.widget.SearchView
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
//import org.koin.android.architecture.ext.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: GenericFragment() {

    private val userViewModel by viewModel<SearchViewModel>()


    override fun layoutRes() = R.layout.fragment_search

    override fun init() {
        setupNav(toolbar)

        setupSearchView()

        userViewModel.search("Cocina")


//        viewModel.search("Cocina")
//        viewModel.productsLiveData.observe(this, Observer {
//            Log.e("sd","asd")
//        })

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