package com.marcelocuevas.mercadolibrechallenge.framework.datasource

import android.content.Context
import androidx.annotation.WorkerThread
import com.marcelocuevas.data.datasource.LocalSearchDataSource
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.db.SearchDBEntity
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.db.SearchDao
import com.marcelocuevas.domain.model.SearchModel

class RoomSearchDataSource(val context: Context, private val searchDao: SearchDao): LocalSearchDataSource {

    @WorkerThread
    override suspend fun saveSearch(search: SearchModel) =
        searchDao.saveSearch(SearchDBEntity(0,search.query))

    override suspend fun getAllSearches(): List<SearchModel> =
        searchDao.getAllSearches().map { SearchModel(it.query) }
}