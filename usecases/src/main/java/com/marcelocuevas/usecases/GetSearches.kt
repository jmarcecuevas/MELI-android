package com.marcelocuevas.usecases

import repository.SearchRepository

class GetSearches(private val repository: SearchRepository) {

    suspend operator fun invoke() =
        repository.getAllSearches()
}