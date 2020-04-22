package repository

import model.ItemModel
import model.Result

interface SearchRepository {

    suspend fun search(query: String): Result<List<ItemModel>>
}