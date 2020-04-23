package com.marcelocuevas.domain.repository

import com.marcelocuevas.domain.model.ItemModel
import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.SearchModel

interface SearchRepository {

    suspend fun search(query: String): Result<List<ItemModel>>

    suspend fun saveSearch(query: String)

    suspend fun getAllSearches(): List<SearchModel>
}