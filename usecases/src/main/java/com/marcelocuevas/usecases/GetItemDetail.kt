package com.marcelocuevas.usecases

import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.detail.ItemDetailModel
import com.marcelocuevas.domain.repository.ItemRepository
import java.io.IOException

class GetItemDetail(private val itemRepository: ItemRepository) {

    suspend operator fun invoke(id: String): Result<ItemDetailModel> {
        val item = itemRepository.itemDetail(id)
        val reviews = itemRepository.reviewsForItem(id)
        val description =itemRepository.descriptionForItem(id)

        if (item is Result.Success && reviews is Result.Success && description is Result.Success)
            return Result.Success(ItemDetailModel(item.data, description.data, reviews.data))
        return Result.Error(IOException("An error ocurred"))
    }
}
