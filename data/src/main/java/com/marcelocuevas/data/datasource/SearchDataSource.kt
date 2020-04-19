package com.marcelocuevas.data.datasource

import com.marcelocuevas.data.model.ProductResponse
import model.Result

interface SearchDataSource {

    suspend fun search(query: String): Result<List<ProductResponse>>
}