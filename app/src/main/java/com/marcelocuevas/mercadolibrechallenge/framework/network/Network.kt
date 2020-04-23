package com.marcelocuevas.mercadolibrechallenge.framework.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.marcelocuevas.mercadolibrechallenge.framework.api.ItemAPI
import com.marcelocuevas.mercadolibrechallenge.framework.api.SearchAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val SEARCH_BASE_URL = "https://api.mercadolibre.com/sites/MLA/"
private const val ITEM_BASE_URL = "https://api.mercadolibre.com/"

fun makeSearchAPI(connectivityInterceptor: ConnectivityInterceptor): SearchAPI =
    retrofitClient(SEARCH_BASE_URL, connectivityInterceptor).create(SearchAPI::class.java)

fun makeItemAPI(connectivityInterceptor: ConnectivityInterceptor): ItemAPI =
    retrofitClient(ITEM_BASE_URL, connectivityInterceptor).create(ItemAPI::class.java)

fun retrofitClient(baseUrl: String, connectivityInterceptor: ConnectivityInterceptor): Retrofit =
    Retrofit.Builder()
        .client(httpClient(connectivityInterceptor))
        .baseUrl(baseUrl)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun httpClient(connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(requestInterceptor())
        //.addInterceptor(httpLogginInterceptor())
        .addInterceptor(connectivityInterceptor)
        .build()
}

private fun requestInterceptor(): Interceptor {
    return Interceptor { chain ->
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
}

private fun httpLogginInterceptor(): Interceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLogginInterceptor()
}



