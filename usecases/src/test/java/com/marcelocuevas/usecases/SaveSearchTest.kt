package com.marcelocuevas.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.marcelocuevas.domain.repository.SearchRepository

class SaveSearchTest {

    private lateinit var repository: SearchRepository
    private lateinit var saveSearch: SaveSearch

    @Before
    fun setup() {
        repository = mock()
        saveSearch = SaveSearch(repository)
    }

    @Test
    fun whenUseCaseIsInvokedShoudlCallRepository() {
        val query = "query"

        runBlocking {
            saveSearch.invoke(query)

            verify(repository).saveSearch(query)
        }
    }
}