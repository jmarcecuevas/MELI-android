package com.marcelocuevas.data.datasource

import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.SearchProductDTO

interface SearchDataSource {

    suspend fun search(query: String): Result<SearchProductDTO>
}