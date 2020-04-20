package com.marcelocuevas.data.repository

import com.marcelocuevas.data.datasource.ItemDataSource
import com.marcelocuevas.data.model.DescriptionsResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.ReviewsResponse
import model.Result
import model.detail.Description
import model.detail.ItemDetail
import model.detail.Review
import repository.ItemRepository

class ItemRepositoryImpl(
    private val itemDataSource: ItemDataSource,
    private val mapItemDetailDto: (ItemDetailResponse) -> ItemDetail.Item,
    private val mapDescriptionDto: (DescriptionsResponse) -> Description,
    private val mapReviewsDto: (ReviewsResponse) -> Review
): ItemRepository {

    override suspend fun itemDetail(id: String): Result<ItemDetail.Item> {
       return when (val result: Result<ItemDetailResponse> = itemDataSource.itemDetail(id)) {
           is Result.Success -> Result.Success(mapItemDetailDto(result.data))
           is Result.Error -> Result.Error(result.message)
       }
    }

    override suspend fun descriptionForItem(id: String): Result<Description> {
        return when (val result: Result<DescriptionsResponse> = itemDataSource.itemDescriptions(id)) {
            is Result.Success -> Result.Success(mapDescriptionDto(result.data))
            is Result.Error -> Result.Error(result.message)
        }
    }

    override suspend fun reviewsForItem(id: String): Result<Review> {
        return when (val result: Result<ReviewsResponse> = itemDataSource.itemReviews(id)) {
            is Result.Success -> Result.Success(mapReviewsDto(result.data))
            is Result.Error -> Result.Error(result.message)
        }
    }
}