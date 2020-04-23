package com.marcelocuevas.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.marcelocuevas.domain.repository.SearchRepository

class SearchItemsTest {

    private lateinit var repository: SearchRepository
    private lateinit var searchItems: SearchItems

    @Before
    fun setup() {
        repository = mock()
        searchItems = SearchItems(repository)
    }

    @Test
    fun whenUseCaseIsInvokedShoudlCallRepository() {
        val query = "query"

        runBlocking {
            searchItems.invoke(query)

            verify(repository).search(query)
        }
    }
}