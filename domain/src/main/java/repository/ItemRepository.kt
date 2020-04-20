package repository

import model.detail.Description
import model.Result
import model.detail.ItemDetail
import model.detail.Review

interface ItemRepository {

    suspend fun itemDetail(id: String): Result<ItemDetail.Item>

    suspend fun descriptionForItem(id: String): Result<Description>

    suspend fun reviewsForItem(id: String): Result<Review>
}