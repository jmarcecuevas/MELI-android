package com.marcelocuevas.usecases

import model.Result
import model.detail.ItemDetail
import repository.ItemRepository
import java.io.IOException

class GetItemDetail(private val itemRepository: ItemRepository) {

    suspend operator fun invoke(id: String): Result<ItemDetail> {
        val item = itemRepository.itemDetail(id)
        val reviews = itemRepository.reviewsForItem(id)
        val description =itemRepository.descriptionForItem(id)

        if (item is Result.Success && reviews is Result.Success
            && description is Result.Success) {
            return Result.Success(
                ItemDetail(
                item.data, description.data, reviews.data))
        }
        return Result.Error(IOException(""))
    }
}