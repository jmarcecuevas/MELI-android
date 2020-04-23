package com.marcelocuevas.domain.repository

import com.marcelocuevas.domain.model.detail.DescriptionModel
import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.detail.ItemDetailModel
import com.marcelocuevas.domain.model.detail.ReviewModel

interface ItemRepository {

    suspend fun itemDetail(id: String): Result<ItemDetailModel.ItemModel>

    suspend fun descriptionForItem(id: String): Result<DescriptionModel>

    suspend fun reviewsForItem(id: String): Result<ReviewModel>
}