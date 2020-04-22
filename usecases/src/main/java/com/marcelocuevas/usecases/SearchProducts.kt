package com.marcelocuevas.usecases

import model.ItemModel
import repository.SearchRepository
import model.Result

class SearchProducts(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String): Result<List<ItemModel>> {
        return repository.search(query)
    }
}