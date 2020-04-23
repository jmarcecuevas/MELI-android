package com.marcelocuevas.mercadolibrechallenge.framework.datasource

import com.marcelocuevas.data.datasource.SearchDataSource
import com.marcelocuevas.data.model.ItemResponse
import model.Result
import com.marcelocuevas.mercadolibrechallenge.framework.api.SearchAPI
import com.marcelocuevas.mercadolibrechallenge.framework.network.NoConnectivityException
import timber.log.Timber
import java.io.IOException

class NetworkSearchDataSource(private val searchAPI: SearchAPI):
    SearchDataSource {

    override suspend fun search(query: String): Result<List<ItemResponse>> {
        try {
            val response = searchAPI.searchAsync(query).await()
            if (response.isSuccessful) {
                response.body()?.let { return Result.Success(it.results) }
            }
            Timber.w("An error ocurred in search")
            return Result.Error(IOException("An error ocurred"))
        } catch (e: NoConnectivityException) {
            return Result.Error(IOException(e.message))
        }
    }
}