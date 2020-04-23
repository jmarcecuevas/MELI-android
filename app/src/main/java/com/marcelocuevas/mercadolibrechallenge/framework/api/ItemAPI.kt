package com.marcelocuevas.mercadolibrechallenge.framework.api

import com.marcelocuevas.data.model.DescriptionsResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.ReviewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemAPI {

    @GET("items/{id}")
    fun itemAsync(@Path("id") id: String): Deferred<Response<ItemDetailResponse>>

    @GET("items/{id}/descriptions")
    fun descriptionsAsync(@Path("id") id: String): Deferred<Response<List<DescriptionsResponse>>>

    @GET("reviews/item/{id}")
    fun reviewsAsync(@Path("id") id: String): Deferred<Response<ReviewsResponse>>
}