package com.marcelocuevas.usecases

import com.marcelocuevas.domain.model.ItemModel
import com.marcelocuevas.domain.repository.SearchRepository
import com.marcelocuevas.domain.model.Result

class SearchItems(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String): Result<List<ItemModel>> {
        return repository.search(query)
    }
}