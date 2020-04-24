package com.marcelocuevas.mercadolibrechallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marcelocuevas.domain.model.ItemModel
import com.marcelocuevas.domain.model.dictionary.Dictionary
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchResultsViewModel
import com.marcelocuevas.mercadolibrechallenge.utils.TestCoroutineRule
import com.marcelocuevas.usecases.SearchItems
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.marcelocuevas.domain.model.Result
import com.nhaarman.mockitokotlin2.*
import java.io.IOException

class SearchResultsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var searchItems: SearchItems
    private lateinit var dictionary: Dictionary
    private lateinit var mapItemDomain: (ItemModel, Dictionary) -> (ItemUIModel)

    private lateinit var viewModel: SearchResultsViewModel
    private lateinit var itemsObserver: Observer<List<ItemUIModel>>
    private lateinit var errorObserver: Observer<Boolean>
    private lateinit var loadingObsever: Observer<Boolean>

    @Before
    fun setup() {
        searchItems = mock()
        dictionary = mock()
        mapItemDomain = mock()
        itemsObserver = mock()
        errorObserver = mock()
        loadingObsever = mock()

        viewModel = SearchResultsViewModel(searchItems,
            dictionary, mapItemDomain)
        viewModel.items.observeForever(itemsObserver)
        viewModel.hasError.observeForever(errorObserver)
        viewModel.loading.observeForever(loadingObsever)
    }

    @Test
    fun `when calling onStart it should save queryText in viewmodel`() {
        viewModel.onStart(QUERY)

        assertThat(viewModel.queryToSearch).isEqualTo(QUERY)
    }

    @Test
    fun `when search is success it should update items observer`() {
        testCoroutineRule.runBlockingTest {
            val list = emptyList<ItemModel>()
            whenever(searchItems(any())).
                    thenReturn(Result.Success(list))

            viewModel.search()

            verify(itemsObserver).onChanged(list.map {
                mapItemDomain(it, dictionary)
            })
        }
    }

    @Test
    fun `when search is success it should change loading state`() {
        testCoroutineRule.runBlockingTest {
            whenever(searchItems(any()))
                .thenReturn(Result.Success(emptyList()))

            viewModel.search()

            verify(loadingObsever).onChanged(false)
        }
    }

    @Test
    fun `when search is not success it should show error`() {
        testCoroutineRule.runBlockingTest {
            whenever(searchItems(any()))
                .thenReturn(Result.Error(IOException()))

            viewModel.search()

            verify(loadingObsever).onChanged(false)
            verify(errorObserver).onChanged(true)
        }
    }

    @After
    fun tearDown() {
        viewModel.items.removeObserver(itemsObserver)
        viewModel.loading.removeObserver(loadingObsever)
        viewModel.hasError.removeObserver(errorObserver)
    }


    private companion object {
        const val QUERY = "motorola"
    }
}