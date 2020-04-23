package com.marcelocuevas.mercadolibrechallenge.framework.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface SearchDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveSearch(search: SearchDBEntity)

    @Query("SELECT * FROM search")
    suspend fun getAllSearches(): List<SearchDBEntity>
}