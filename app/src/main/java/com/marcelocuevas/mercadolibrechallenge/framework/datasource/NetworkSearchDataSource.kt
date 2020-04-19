package com.marcelocuevas.mercadolibrechallenge.framework.datasource

import com.marcelocuevas.data.datasource.SearchDataSource
import com.marcelocuevas.data.model.ProductResponse
import model.Result
import com.marcelocuevas.mercadolibrechallenge.framework.api.SearchAPI
import com.marcelocuevas.mercadolibrechallenge.framework.network.NoConnectivityException
import java.io.IOException

class NetworkSearchDataSource(private val searchAPI: SearchAPI):
    SearchDataSource {

    override suspend fun search(query: String): Result<List<ProductResponse>> {
        try {
            val response = searchAPI.searchAsync(query).await()
            if (response.isSuccessful) {
                response.body()?.let { return Result.Success(it.results) }
            }
            return Result.Error(IOException("Ha ocurrido un error"))
        } catch (e: NoConnectivityException) {
            return Result.Error(IOException("No hay internet"))
        }
    }
}