package com.marcelocuevas.mercadolibrechallenge.framework.api

import com.marcelocuevas.data.model.SearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPI {

    @GET("search")
    fun searchAsync(@Query("q") q: String): Deferred<Response<SearchResponse>>
}