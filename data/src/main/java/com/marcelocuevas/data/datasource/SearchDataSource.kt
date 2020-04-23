package com.marcelocuevas.data.datasource

import com.marcelocuevas.data.model.ItemResponse
import com.marcelocuevas.domain.model.Result

interface SearchDataSource {

    suspend fun search(query: String): Result<List<ItemResponse>>
}