package com.marcelocuevas.usecases

import model.Item
import repository.SearchRepository
import model.Result

class SearchProducts(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String): Result<List<Item>> {
        return repository.search(query)
    }
}