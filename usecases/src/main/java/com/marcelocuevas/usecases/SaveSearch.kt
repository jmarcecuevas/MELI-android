package com.marcelocuevas.usecases

import com.marcelocuevas.domain.repository.SearchRepository

class SaveSearch(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String) =
        repository.saveSearch(query)
}