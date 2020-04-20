package repository

import model.Item
import model.Result

interface SearchRepository {

    suspend fun search(query: String): Result<List<Item>>
}