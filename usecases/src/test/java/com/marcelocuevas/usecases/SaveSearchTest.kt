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
    fun `when usecase is invoked should call repository`() {
        runBlocking {
            saveSearch.invoke(QUERY)

            verify(repository).saveSearch(QUERY)
        }
    }

    private companion object {
        const val QUERY = "motorola"
    }
}