package repository

import model.detail.DescriptionModel
import model.Result
import model.detail.ItemDetailModel
import model.detail.ReviewModel

interface ItemRepository {

    suspend fun itemDetail(id: String): Result<ItemDetailModel.ItemModel>

    suspend fun descriptionForItem(id: String): Result<DescriptionModel>

    suspend fun reviewsForItem(id: String): Result<ReviewModel>
}