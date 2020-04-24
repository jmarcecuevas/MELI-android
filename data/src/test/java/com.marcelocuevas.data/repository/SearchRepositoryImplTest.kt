package com.marcelocuevas.data.repository

import com.marcelocuevas.data.datasource.LocalSearchDataSource
import com.marcelocuevas.data.datasource.SearchDataSource
import com.marcelocuevas.data.model.ItemResponse
import com.marcelocuevas.domain.model.ItemModel
import com.marcelocuevas.domain.model.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.marcelocuevas.domain.model.SearchModel
import com.nhaarman.mockitokotlin2.verify
import java.io.IOException

class SearchRepositoryImplTest {

    private var apiDataSource: SearchDataSource = mock()
    private var localDataSource: LocalSearchDataSource = mock()
    private var mapItemDto: (ItemResponse) -> ItemModel = mock()

    private var repository = SearchRepositoryImpl(apiDataSource,
        localDataSource, mapItemDto)

    @Test
    fun `search should return items when api call is successful`() {
        runBlocking {
            val itemsList: List<ItemResponse> = emptyList()
            whenever(apiDataSource.search(QUERY))
                .thenReturn(Result.Success(itemsList))

            val actualResponse = repository.search(QUERY)

            assertThat(actualResponse).isEqualTo(
                Result.Success(itemsList.map { mapItemDto(it) }))
        }
    }

    @Test
    fun `search should return unmapped error when api call is not successful`() {
        runBlocking {
            val exception = IOException("An error ocurred")
            whenever(apiDataSource.search(QUERY))
                .thenReturn(Result.Error(exception))

            val actualResponse = repository.search(QUERY)

            assertThat(actualResponse).isEqualTo(Result.Error(exception))
        }
    }

    @Test
    fun `saveSearch should create a SearchModel and call localDatasource`() {
        runBlocking {
            repository.saveSearch(QUERY)

            verify(localDataSource).saveSearch(SearchModel(QUERY))
        }
    }

    @Test
    fun `getAllSearches should call to localDataSource to retrieve recent searches`() {
        runBlocking {
            repository.getAllSearches()

            verify(localDataSource).getAllSearches()
        }
    }

    private companion object {
        const val QUERY = "motorola"
    }
}