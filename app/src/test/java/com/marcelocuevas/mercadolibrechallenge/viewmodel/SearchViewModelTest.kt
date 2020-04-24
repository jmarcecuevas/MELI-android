package com.marcelocuevas.mercadolibrechallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marcelocuevas.domain.model.SearchModel
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import com.marcelocuevas.mercadolibrechallenge.utils.TestCoroutineRule
import com.marcelocuevas.usecases.GetSearches
import com.marcelocuevas.usecases.SaveSearch
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var saveSearch: SaveSearch
    private lateinit var getSearches: GetSearches
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchesObserver: Observer<List<SearchModel>>

    @Before
    fun setup() {
        searchesObserver = mock()
        saveSearch = mock()
        getSearches = mock()
        viewModel = SearchViewModel(saveSearch, getSearches)
        viewModel.searches.observeForever(searchesObserver)
    }

    @Test
    fun `when calling getAllSearches and the usecase returns empty list then it should return an empty list too`() {
        testCoroutineRule.runBlockingTest {
            testCoroutineRule.runBlockingTest {
                whenever(getSearches.invoke()).thenReturn(emptyList())

                viewModel.getAllSearches()

                verify(searchesObserver).onChanged(emptyList())
            }
        }
    }

    @Test
    fun `when calling getAllSearches and the usecase returns a not empty list then it should return the list reversed`() {
        testCoroutineRule.runBlockingTest {
            val list = listOf(SearchModel("motorola"), SearchModel("samsung s10"))
            whenever(getSearches.invoke()).thenReturn(list)

            viewModel.getAllSearches()

            verify(searchesObserver).onChanged(list.reversed())
        }
    }

    @Test
    fun `when searchButtonClicked is called it should call the appropiate usecase`() {
        testCoroutineRule.runBlockingTest {
            viewModel.searchButtonClicked(QUERY)

            verify(saveSearch).invoke(QUERY)
        }
    }

    @After
    fun tearDown() {
        viewModel.searches.removeObserver(searchesObserver)
    }


    private companion object {
        const val QUERY = "motorola"
    }

}