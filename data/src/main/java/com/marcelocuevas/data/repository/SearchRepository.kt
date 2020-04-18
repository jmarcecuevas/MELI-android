package com.marcelocuevas.data.repository

import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.SearchProductDTO

interface SearchRepository {

    suspend fun search(query: String): Result<SearchProductDTO>
}