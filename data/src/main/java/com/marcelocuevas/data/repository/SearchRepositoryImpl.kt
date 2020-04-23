package com.marcelocuevas.data.repository

import com.marcelocuevas.data.datasource.LocalSearchDataSource
import com.marcelocuevas.data.datasource.SearchDataSource
import com.marcelocuevas.data.model.ItemResponse
import model.ItemModel
import model.Result
import model.SearchModel
import repository.SearchRepository

class SearchRepositoryImpl (
    private val apiDataSource: SearchDataSource,
    private val localDataSource: LocalSearchDataSource,
    private val mapItemDto: (ItemResponse) -> ItemModel
): SearchRepository {

    override suspend fun search(query: String): Result<List<ItemModel>> {
        return when (val value = apiDataSource.search(query)) {
            is Result.Success -> Result.Success(value.data.map { mapItemDto(it) })
            is Result.Error -> Result.Error(value.message)
        }
    }

    override suspend fun saveSearch(query: String) =
        localDataSource.saveSearch(SearchModel(query))

    override suspend fun getAllSearches(): List<SearchModel> =
        localDataSource.getAllSearches()
}