package com.marcelocuevas.usecases

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.detail.DescriptionModel
import com.marcelocuevas.domain.model.detail.ItemDetailModel
import com.marcelocuevas.domain.model.detail.ReviewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import com.marcelocuevas.domain.repository.ItemRepository

class GetItemDetailTest {

    private lateinit var repository: ItemRepository
    private lateinit var getItemDetail: GetItemDetail

    private lateinit var  successDetailResponse: Result.Success<ItemDetailModel.ItemModel>
    private lateinit var successReviewsResponse: Result.Success<ReviewModel>
    private lateinit var successDescriptionResponse: Result.Success<DescriptionModel>

    @Before
    fun setup() {
        repository = mock()
        getItemDetail = GetItemDetail(repository)

        setupSuccessResponses()
        setupDataToResponses()
    }

    @Test
    fun whenUseCaseIsInvokedShoudCallRepositoryMethods() {
        runBlocking {
            getItemDetail.invoke(ITEM_ID)

            verify(repository).itemDetail(ITEM_ID)
            verify(repository).reviewsForItem(ITEM_ID)
            verify(repository).descriptionForItem(ITEM_ID)
        }
    }

    @Test
    fun whenAllRepositoryCallsAreSuccessShouldReturnSuccess() {
        runBlocking {
            allCallsToRepositoryAreSuccess()

            val expectedResponse: Result<ItemDetailModel> =
                Result.Success(ItemDetailModel(
                    successDetailResponse.data,
                    successDescriptionResponse.data,
                    successReviewsResponse.data)
                )
            val actualResponse = getItemDetail.invoke(ITEM_ID)

            Assert.assertEquals(expectedResponse, actualResponse)
        }
    }

    @Test
    fun whenAtLeastOneRepositoryCallHasErrorShouldReturnError() {
        runBlocking {
            val error: Result.Error = mock()
            whenever(repository.itemDetail(ITEM_ID)).thenReturn(error)

            val detailResponse = getItemDetail(ITEM_ID)

            Assert.assertTrue(detailResponse is Result.Error)
        }
    }

    private fun setupSuccessResponses() {
        successDetailResponse = mock()
        successReviewsResponse = mock()
        successDescriptionResponse = mock()
    }

    private fun setupDataToResponses() {
        val detailData: ItemDetailModel.ItemModel = mock()
        val reviewData: ReviewModel = mock()
        val descriptionData: DescriptionModel = mock()

        whenever(successDetailResponse.data).thenReturn(detailData)
        whenever(successDescriptionResponse.data).thenReturn(descriptionData)
        whenever(successReviewsResponse.data).thenReturn(reviewData)
    }

    private fun allCallsToRepositoryAreSuccess() {
        runBlocking {
            whenever(repository.itemDetail(ITEM_ID)).thenReturn(successDetailResponse)
            whenever(repository.reviewsForItem(ITEM_ID)).thenReturn(successReviewsResponse)
            whenever(repository.descriptionForItem(ITEM_ID)).thenReturn(successDescriptionResponse)
        }
    }

    companion object{
        private const val ITEM_ID = "MT32422441"
    }
}