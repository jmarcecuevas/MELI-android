package com.marcelocuevas.usecases

import repository.ItemRepository

class GetItemDetail(private val itemRepository: ItemRepository) {

    suspend operator fun invoke(id: String) {

    }
}