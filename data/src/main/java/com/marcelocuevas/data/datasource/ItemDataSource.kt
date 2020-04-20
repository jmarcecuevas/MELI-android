package com.marcelocuevas.data.datasource

import com.marcelocuevas.data.model.DescriptionsResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.ReviewsResponse
import model.Result

interface ItemDataSource {

    suspend fun itemDetail(itemID: String): Result<ItemDetailResponse>

    suspend fun itemDescriptions(itemID: String): Result<DescriptionsResponse>

    suspend fun itemReviews(itemID: String): Result<ReviewsResponse>
}