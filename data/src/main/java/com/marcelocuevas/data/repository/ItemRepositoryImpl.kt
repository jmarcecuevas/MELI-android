package com.marcelocuevas.data.repository

import com.marcelocuevas.data.datasource.ItemDataSource
import com.marcelocuevas.data.model.DescriptionsResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.ReviewsResponse
import model.Result
import model.detail.DescriptionModel
import model.detail.ItemDetailModel
import model.detail.ReviewModel
import repository.ItemRepository

class ItemRepositoryImpl(
    private val itemDataSource: ItemDataSource,
    private val mapItemDetailDto: (ItemDetailResponse) -> ItemDetailModel.ItemModel,
    private val mapDescriptionDto: (DescriptionsResponse) -> DescriptionModel,
    private val mapReviewsDto: (ReviewsResponse) -> ReviewModel
): ItemRepository {

    override suspend fun itemDetail(id: String): Result<ItemDetailModel.ItemModel> {
       return when (val result: Result<ItemDetailResponse> = itemDataSource.itemDetail(id)) {
           is Result.Success -> Result.Success(mapItemDetailDto(result.data))
           is Result.Error -> Result.Error(result.message)
       }
    }

    override suspend fun descriptionForItem(id: String): Result<DescriptionModel> {
        return when (val result: Result<DescriptionsResponse> = itemDataSource.itemDescriptions(id)) {
            is Result.Success -> Result.Success(mapDescriptionDto(result.data))
            is Result.Error -> Result.Error(result.message)
        }
    }

    override suspend fun reviewsForItem(id: String): Result<ReviewModel> {
        return when (val result: Result<ReviewsResponse> = itemDataSource.itemReviews(id)) {
            is Result.Success -> Result.Success(mapReviewsDto(result.data))
            is Result.Error -> Result.Error(result.message)
        }
    }
}