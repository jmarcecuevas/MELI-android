package repository

import model.Product
import model.Result

interface SearchRepository {

    suspend fun search(query: String): Result<List<Product>>
}