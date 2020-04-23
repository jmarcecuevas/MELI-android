package com.marcelocuevas.mercadolibrechallenge.framework.datasource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [SearchDBEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase: RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "searches.db"

        private var instance: LocalDatabase? = null

        private fun create(context: Context): LocalDatabase =
            Room.databaseBuilder(context, LocalDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): LocalDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun searchDAO(): SearchDao
}