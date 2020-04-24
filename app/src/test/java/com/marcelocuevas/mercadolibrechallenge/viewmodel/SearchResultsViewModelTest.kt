package com.marcelocuevas.mercadolibrechallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marcelocuevas.domain.model.SearchModel
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import com.marcelocuevas.mercadolibrechallenge.utils.TestCoroutineRule
import com.marcelocuevas.usecases.GetSearches
import com.marcelocuevas.usecases.SaveSearch
import org.junit.Rule

class SearchResultsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var searchPro: SaveSearch
    private lateinit var getSearches: GetSearches
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchesObserver: Observer<List<SearchModel>>


}