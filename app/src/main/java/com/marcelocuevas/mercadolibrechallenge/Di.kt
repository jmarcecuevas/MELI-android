package com.marcelocuevas.mercadolibrechallenge

import com.marcelocuevas.data.datasource.ItemDataSource
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
import com.marcelocuevas.mercadolibrechallenge.presentation.mapper.mapItemDetailDomain
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.ItemViewModel
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.SearchViewModel
import com.marcelocuevas.usecases.GetItemDetail
import com.marcelocuevas.usecases.SearchProducts
import model.Item
import model.detail.Description
import model.detail.ItemDetail
import model.detail.Review
import model.dictionary.Dictionary
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import repository.ItemRepository
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetail as ItemDetailUIModel

val dataModule = module {
    single<SearchRepository> { SearchRepositoryImpl(get(), makeItemDataMapper()) }
    single<ItemRepository> {   ItemRepositoryImpl(get(),
                                makeItemDetailDataMapper(),
                                makeDescriptionDataMapper(),
                                makeReviewDataMapper()) }
}

val useCaseModule = module {
    factory { SearchProducts(get()) }
    factory { GetItemDetail(get()) }
}

val appModule = module {
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(get())}
    single { SearchAPI.invoke(get()) }
    single { ItemAPI.invoke(get()) }

    single<SearchDataSource> { NetworkSearchDataSource(get()) }
    single<ItemDataSource> { NetworkItemDataSource(get())  }

    single<Dictionary> {
        AndroidDictionary(
            androidContext()
        )
    }

    factory { SearchViewModel(get(), get(), makeItemDomainMapper()) }
    factory { ItemViewModel(get(), get(), makeItemDetailDomainMapper()) }
}

private fun makeItemDetailDomainMapper(): (ItemDetail, Dictionary) -> ItemDetailUIModel = { itemDetail, dic ->
    mapItemDetailDomain(itemDetail, dic)
}

private fun makeItemDomainMapper(): (Item, Dictionary) -> ItemUIModel = { itemDomain, dic ->
    mapItemDomain(itemDomain,dic)
}

private fun makeItemDataMapper(): (ItemResponse) -> Item = { itemDto ->
    mapItemDto(itemDto)
}

private fun makeItemDetailDataMapper(): (ItemDetailResponse) -> ItemDetail.Item = { itemDetailDto ->
    mapItemDetailDto(itemDetailDto)
}

private fun makeDescriptionDataMapper(): (DescriptionsResponse) -> Description = { descriptionDto ->
    mapDescriptionDto(descriptionDto)
}

private fun makeReviewDataMapper(): (ReviewsResponse) -> Review = { reviewDto ->
    mapReviewDto(reviewDto)
}
