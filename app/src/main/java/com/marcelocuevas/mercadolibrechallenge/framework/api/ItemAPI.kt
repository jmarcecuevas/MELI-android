package com.marcelocuevas.mercadolibrechallenge.framework.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.marcelocuevas.data.model.DescriptionsResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.ReviewsResponse
import com.marcelocuevas.mercadolibrechallenge.framework.network.ConnectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemAPI {

    @GET("items/{id}")
    fun itemAsync(@Path("id") id: String): Deferred<Response<ItemDetailResponse>>

    @GET("items/{id}/descriptions")
    fun descriptionsAsync(@Path("id") id: String): Deferred<Response<DescriptionsResponse>>

    @GET("reviews/item/{id}")
    fun reviewsAsync(@Path("id") id: String): Deferred<Response<ReviewsResponse>>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ItemAPI {
            val requestInterceptor = Interceptor {chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.mercadolibre.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ItemAPI::class.java)
        }
    }
}