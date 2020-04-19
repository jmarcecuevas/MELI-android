package com.marcelocuevas.data.repository

import com.marcelocuevas.data.datasource.SearchDataSource
import com.marcelocuevas.data.model.mapToDomain
import model.Product
import model.Result
import repository.SearchRepository

class SearchRepositoryImpl (
    private val searchDataSource: SearchDataSource
): SearchRepository {

    override suspend fun search(query: String): Result<List<Product>> {
        return when (val value = searchDataSource.search(query)) {
            is Result.Success -> Result.Success(value.data.mapToDomain())
            is Result.Error -> Result.Error(value.message)
        }
    }
}