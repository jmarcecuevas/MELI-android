package com.marcelocuevas.data.datasource

import model.SearchModel

interface LocalSearchDataSource {

    suspend fun saveSearch(search: SearchModel)

    suspend fun getAllSearches(): List<SearchModel>
}