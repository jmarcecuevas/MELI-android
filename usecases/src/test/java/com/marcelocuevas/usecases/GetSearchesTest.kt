package com.marcelocuevas.usecases

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.marcelocuevas.domain.repository.SearchRepository

class GetSearchesTest {

    private lateinit var repository: SearchRepository
    private lateinit var getSearches: GetSearches

    @Before
    fun setup() {
        repository = mock()
        getSearches = GetSearches(repository)
    }

    @Test
     fun whenUseCaseIsInvokedShoudlCallRepository() {
        runBlocking {
            getSearches.invoke()

            verify(repository).getAllSearches()
        }
    }
}