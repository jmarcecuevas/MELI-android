package com.marcelocuevas.mercadolibrechallenge

import com.marcelocuevas.data.datasource.SearchDataSource
import repository.SearchRepository
import com.marcelocuevas.data.repository.SearchRepositoryImpl
import com.marcelocuevas.mercadolibrechallenge.framework.api.SearchAPI
import com.marcelocuevas.mercadolibrechallenge.framework.network.ConnectivityInterceptor
import com.marcelocuevas.mercadolibrechallenge.framework.network.ConnectivityInterceptorImpl
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.NetworkSearchDataSource
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import com.marcelocuevas.usecases.SearchProducts
import org.koin.dsl.module

val dataModule = module {
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { SearchProducts(get()) }
}

val appModule = module {
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(get())}
    single { SearchAPI.invoke(get()) }
    single<SearchDataSource> {
        NetworkSearchDataSource(
            get()
        )
    }
    factory { SearchViewModel(get()) }
}