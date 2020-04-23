package com.marcelocuevas.data.datasource

import com.marcelocuevas.domain.model.SearchModel

interface LocalSearchDataSource {

    suspend fun saveSearch(search: SearchModel)

    suspend fun getAllSearches(): List<SearchModel>
}