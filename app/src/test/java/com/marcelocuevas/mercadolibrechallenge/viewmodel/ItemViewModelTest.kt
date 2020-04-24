package com.marcelocuevas.mercadolibrechallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.detail.ItemDetailModel
import com.marcelocuevas.domain.model.dictionary.Dictionary
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.ItemViewModel
import com.marcelocuevas.mercadolibrechallenge.utils.TestCoroutineRule
import com.marcelocuevas.usecases.GetItemDetail
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ItemViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var getItemDetail: GetItemDetail
    private lateinit var dictionary: Dictionary
    private lateinit var mapItemDetailDomain: (ItemDetailModel, Dictionary) -> (ItemDetailUIModel)

    private lateinit var viewModel: ItemViewModel
    private lateinit var itemObserver: Observer<ItemDetailUIModel>
    private lateinit var errorObserver: Observer<Boolean>
    private lateinit var loadingObsever: Observer<Boolean>

    @Before
    fun setup() {
        getItemDetail = mock()
        dictionary = mock()
        mapItemDetailDomain = mock()
        itemObserver = mock()
        errorObserver = mock()
        loadingObsever = mock()

        viewModel = ItemViewModel(getItemDetail,
            dictionary, mapItemDetailDomain)
        viewModel.item.observeForever(itemObserver)
        viewModel.error.observeForever(errorObserver)
        viewModel.loading.observeForever(loadingObsever)
    }

    @Test
    fun `when calling onStart it should save itemID in viewmodel`() {
        viewModel.onStart(ITEM_ID)

        assertThat(viewModel.id).isEqualTo(ITEM_ID)
    }

    @Test
    fun `when getItemDetail is success it should update the item observer`() {
        testCoroutineRule.runBlockingTest {
            val item: ItemDetailModel = mock()
            whenever(getItemDetail(any())).
                thenReturn(Result.Success(item))

            viewModel.itemDetail()

            verify(itemObserver).onChanged(
                mapItemDetailDomain(item, dictionary))
        }
    }

    @Test
    fun `when getItemDetail is success it should change loading state`() {
        testCoroutineRule.runBlockingTest {
            val item: ItemDetailModel = mock()
            whenever(getItemDetail(any()))
                .thenReturn(Result.Success(item))

            viewModel.itemDetail()

            verify(loadingObsever).onChanged(false)
        }
    }

    @Test
    fun `when getItemDetail is not success it should show error`() {
        testCoroutineRule.runBlockingTest {
            whenever(getItemDetail(any()))
                .thenReturn(Result.Error(IOException()))

            viewModel.itemDetail()

            verify(loadingObsever).onChanged(false)
            verify(errorObserver).onChanged(true)
        }
    }

    @After
    fun tearDown() {
        viewModel.item.removeObserver(itemObserver)
        viewModel.loading.removeObserver(loadingObsever)
        viewModel.error.removeObserver(errorObserver)
    }


    private companion object {
        const val ITEM_ID = "MT2343422"
    }
}