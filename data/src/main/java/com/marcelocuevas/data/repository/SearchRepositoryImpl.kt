package com.marcelocuevas.data.repository

import com.marcelocuevas.data.datasource.SearchDataSource
import com.marcelocuevas.data.model.ItemResponse
import model.Item
import model.Result
import repository.SearchRepository

class SearchRepositoryImpl (
    private val searchDataSource: SearchDataSource,
    private val mapItemDto: (ItemResponse) -> Item
): SearchRepository {

    override suspend fun search(query: String): Result<List<Item>> {
        return when (val value = searchDataSource.search(query)) {
            is Result.Success -> Result.Success(value.data.map { mapItemDto(it) })
            is Result.Error -> Result.Error(value.message)
        }
    }
}