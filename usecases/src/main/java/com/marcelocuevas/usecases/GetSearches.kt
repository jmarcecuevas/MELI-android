package com.marcelocuevas.usecases

import com.marcelocuevas.domain.repository.SearchRepository

class GetSearches(private val repository: SearchRepository) {

    suspend operator fun invoke() =
        repository.getAllSearches()
}