package com.marcelocuevas.data.repository

import com.marcelocuevas.data.datasource.SearchDataSource
import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.SearchProductDTO

class SearchRepositoryImpl (
    private val searchDataSource: SearchDataSource
): SearchRepository {

    override suspend fun search(query: String): Result<SearchProductDTO> {
        return searchDataSource.search(query)
    }
}