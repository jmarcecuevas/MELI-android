package com.marcelocuevas.mercadolibrechallenge.framework.datasource

import com.marcelocuevas.data.datasource.ItemDataSource
import com.marcelocuevas.data.model.DescriptionsResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.ReviewsResponse
import com.marcelocuevas.mercadolibrechallenge.framework.api.ItemAPI
import com.marcelocuevas.mercadolibrechallenge.framework.network.NoConnectivityException
import model.Result
import java.io.IOException

class NetworkItemDataSource(private val itemAPI: ItemAPI):
    ItemDataSource {

    override suspend fun itemDetail(itemID: String): Result<ItemDetailResponse> {
        try {
            val response = itemAPI.itemAsync(itemID).await()
            if (response.isSuccessful) {
                response.body()?.let { return Result.Success(it) }
            }
            return Result.Error(IOException("Ha ocurrido un error"))
        } catch (e: NoConnectivityException) {
            return Result.Error(IOException("No hay internet"))
        }
    }

    override suspend fun itemDescriptions(itemID: String): Result<DescriptionsResponse> {
        try {
            val response = itemAPI.descriptionsAsync(itemID).await()
            if (response.isSuccessful) {
                response.body()?.let { return Result.Success(it) }
            }
            return Result.Error(IOException("Ha ocurrido un error"))
        } catch (e: NoConnectivityException) {
            return Result.Error(IOException("No hay internet"))
        }
    }

    override suspend fun itemReviews(itemID: String): Result<ReviewsResponse> {
        try {
            val response = itemAPI.reviewsAsync(itemID).await()
            if (response.isSuccessful) {
                response.body()?.let { return Result.Success(it) }
            }
            return Result.Error(IOException("Ha ocurrido un error"))
        } catch (e: NoConnectivityException) {
            return Result.Error(IOException("No hay internet"))
        }
    }
}