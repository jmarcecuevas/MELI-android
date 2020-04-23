package repository

import model.ItemModel
import model.Result
import model.SearchModel

interface SearchRepository {

    suspend fun search(query: String): Result<List<ItemModel>>

    suspend fun saveSearch(query: String)

    suspend fun getAllSearches(): List<SearchModel>
}