package com.marcelocuevas.mercadolibrechallenge.framework.datasource

import android.content.Context
import com.marcelocuevas.data.datasource.LocalSearchDataSource
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.db.LocalDatabase
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.db.SearchDBEntity
import model.SearchModel

class RoomSearchDataSource(val context: Context): LocalSearchDataSource {

    private val searchDao = LocalDatabase.getInstance(context).searchDAO()

    override suspend fun saveSearch(search: SearchModel) =
        searchDao.saveSearch(SearchDBEntity(0,search.query))

    override suspend fun getAllSearches(): List<SearchModel> =
        searchDao.getAllSearches().map { SearchModel(it.query) }
}