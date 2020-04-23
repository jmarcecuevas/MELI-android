package com.marcelocuevas.data.datasource

import com.marcelocuevas.data.model.ItemResponse
import model.Result
import model.SearchModel

interface SearchDataSource {

    suspend fun search(query: String): Result<List<ItemResponse>>
}