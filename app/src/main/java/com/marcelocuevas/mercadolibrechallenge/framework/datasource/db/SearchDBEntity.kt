package com.marcelocuevas.mercadolibrechallenge.framework.datasource.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "search",
    indices = [Index(value = ["query"], unique = true)])
data class SearchDBEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "query") val query: String
)