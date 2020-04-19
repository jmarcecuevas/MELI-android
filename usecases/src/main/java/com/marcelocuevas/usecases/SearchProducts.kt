package com.marcelocuevas.usecases

import model.Product
import repository.SearchRepository
import model.Result

class SearchProducts(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String): Result<List<Product>> {
        return repository.search(query)
    }
}