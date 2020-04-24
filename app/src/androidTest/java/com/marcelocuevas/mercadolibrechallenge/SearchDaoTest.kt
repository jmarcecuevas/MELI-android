package com.marcelocuevas.mercadolibrechallenge

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.db.LocalDatabase
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.db.SearchDao
import com.google.common.truth.Truth.assertThat
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.db.SearchDBEntity
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchDaoTest {

    private lateinit var localDatabase: LocalDatabase
    private lateinit var searchDao: SearchDao

    @Before
    fun setup() {
        localDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            LocalDatabase::class.java).build()
        searchDao = localDatabase.searchDAO()
    }

    @Test
    fun getAllSearchesReturnsAnEmptyList() {
        runBlocking {
            val response = searchDao.getAllSearches()

            assertThat(response).isEmpty()
        }
    }

    @Test
    fun saveSearchSavesData() {
        runBlocking {
            val searchEntity = SearchDBEntity(0, "motorola")
            searchDao.saveSearch(searchEntity)

            val newList = searchDao.getAllSearches()

            assertThat(newList).isNotEmpty()
        }
    }

    @After
    fun tearDown() {
        localDatabase.close()
    }
}