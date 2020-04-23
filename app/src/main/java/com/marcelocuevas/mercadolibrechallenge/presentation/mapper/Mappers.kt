package com.marcelocuevas.mercadolibrechallenge.presentation.mapper

import com.marcelocuevas.data.mapper.mapDescriptionDto
import com.marcelocuevas.data.mapper.mapItemDetailDto
import com.marcelocuevas.data.mapper.mapItemDto
import com.marcelocuevas.data.mapper.mapReviewDto
import com.marcelocuevas.data.model.DescriptionsResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.ItemResponse
import com.marcelocuevas.data.model.ReviewsResponse
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemUIModel
import com.marcelocuevas.domain.model.ItemModel
import com.marcelocuevas.domain.model.detail.DescriptionModel
import com.marcelocuevas.domain.model.detail.ItemDetailModel
import com.marcelocuevas.domain.model.detail.ReviewModel
import com.marcelocuevas.domain.model.dictionary.Dictionary

fun makeItemDetailDomainMapper(): (ItemDetailModel, Dictionary) -> ItemDetailUIModel = { itemDetail, dic ->
    mapItemDetailDomain(itemDetail, dic)
}

 fun makeItemDomainMapper(): (ItemModel, Dictionary) -> ItemUIModel = { itemDomain, dic ->
    mapItemDomain(itemDomain,dic)
}

 fun makeItemDataMapper(): (ItemResponse) -> ItemModel = { itemDto ->
    mapItemDto(itemDto)
}

 fun makeItemDetailDataMapper(): (ItemDetailResponse) -> ItemDetailModel.ItemModel = { itemDetailDto ->
    mapItemDetailDto(itemDetailDto)
}

 fun makeDescriptionDataMapper(): (DescriptionsResponse) -> DescriptionModel = { descriptionDto ->
    mapDescriptionDto(descriptionDto)
}

 fun makeReviewDataMapper(): (ReviewsResponse) -> ReviewModel = { reviewDto ->
    mapReviewDto(reviewDto)
}
