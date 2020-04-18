package com.marcelocuevas.usecases

import com.marcelocuevas.data.repository.SearchRepository
import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.SearchProductDTO

class SearchProducts(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String): Result<SearchProductDTO> {
        return repository.search(query)
    }
}