package com.marcelocuevas.usecases

import model.ItemModel
import repository.SearchRepository
import model.Result

class SearchItems(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String): Result<List<ItemModel>> {
        return repository.search(query)
    }
}