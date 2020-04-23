package com.marcelocuevas.mercadolibrechallenge

import com.marcelocuevas.data.datasource.ItemDataSource
import com.marcelocuevas.data.datasource.LocalSearchDataSource
import com.marcelocuevas.data.datasource.SearchDataSource
import com.marcelocuevas.data.mapper.mapDescriptionDto
import com.marcelocuevas.data.mapper.mapItemDetailDto
import com.marcelocuevas.data.mapper.mapItemDto
import com.marcelocuevas.data.mapper.mapReviewDto
import com.marcelocuevas.data.model.DescriptionsResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.ItemResponse
import com.marcelocuevas.data.model.ReviewsResponse
import com.marcelocuevas.data.repository.ItemRepositoryImpl
import repository.SearchRepository
import com.marcelocuevas.data.repository.SearchRepositoryImpl
import com.marcelocuevas.mercadolibrechallenge.framework.api.ItemAPI
import com.marcelocuevas.mercadolibrechallenge.framework.api.SearchAPI
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.NetworkItemDataSource
import com.marcelocuevas.mercadolibrechallenge.framework.network.ConnectivityInterceptor
import com.marcelocuevas.mercadolibrechallenge.framework.network.ConnectivityInterceptorImpl
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.NetworkSearchDataSource
import com.marcelocuevas.mercadolibrechallenge.presentation.mapper.mapItemDomain
import com.marcelocuevas.mercadolibrechallenge.framework.AndroidDictionary
import com.marcelocuevas.mercadolibrechallenge.framework.datasource.RoomSearchDataSource
import com.marcelocuevas.mercadolibrechallenge.presentation.mapper.mapItemDetailDomain
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.ItemViewModel
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchResultsViewModel
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import com.marcelocuevas.usecases.GetItemDetail
import com.marcelocuevas.usecases.GetSearches
import com.marcelocuevas.usecases.SaveSearch
import com.marcelocuevas.usecases.SearchProducts
import model.ItemModel
import model.detail.DescriptionModel
import model.detail.ItemDetailModel
import model.detail.ReviewModel
import model.dictionary.Dictionary
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import repository.ItemRepository
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel as ItemDetailUIModel

val dataModule = module {
    //Repositories
    single<SearchRepository> { SearchRepositoryImpl(get(), get(), makeItemDataMapper()) }
    single<ItemRepository> {   ItemRepositoryImpl(get(),
                                makeItemDetailDataMapper(),
                                makeDescriptionDataMapper(),
                                makeReviewDataMapper()) }

}

val useCaseModule = module {
    factory { SearchProducts(get()) }
    factory { GetItemDetail(get()) }
    factory { SaveSearch(get()) }
    factory { GetSearches(get()) }
}

val appModule = module {
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(get())}
    single { SearchAPI.invoke(get()) }
    single { ItemAPI.invoke(get()) }

    //Datasources
    single<SearchDataSource> { NetworkSearchDataSource(get()) }
    single<ItemDataSource> { NetworkItemDataSource(get())  }
    single<LocalSearchDataSource> { RoomSearchDataSource(androidContext()) }

    single<Dictionary> { AndroidDictionary(androidContext()) }

    //ViewModels
    factory { SearchResultsViewModel(get(), get(), makeItemDomainMapper()) }
    factory { ItemViewModel(get(), get(), makeItemDetailDomainMapper()) }
    factory { SearchViewModel(get(), get()) }
}

private fun makeItemDetailDomainMapper(): (ItemDetailModel, Dictionary) -> ItemDetailUIModel = { itemDetail, dic ->
    mapItemDetailDomain(itemDetail, dic)
}

private fun makeItemDomainMapper(): (ItemModel, Dictionary) -> ItemUIModel = { itemDomain, dic ->
    mapItemDomain(itemDomain,dic)
}

private fun makeItemDataMapper(): (ItemResponse) -> ItemModel = { itemDto ->
    mapItemDto(itemDto)
}

private fun makeItemDetailDataMapper(): (ItemDetailResponse) -> ItemDetailModel.ItemModel = { itemDetailDto ->
    mapItemDetailDto(itemDetailDto)
}

private fun makeDescriptionDataMapper(): (DescriptionsResponse) -> DescriptionModel = { descriptionDto ->
    mapDescriptionDto(descriptionDto)
}

private fun makeReviewDataMapper(): (ReviewsResponse) -> ReviewModel = { reviewDto ->
    mapReviewDto(reviewDto)
}
