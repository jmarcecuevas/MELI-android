package com.marcelocuevas.mercadolibrechallenge.presentation.mapper

import model.detail.ItemDetail
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetail as ItemDetailUIModel
import model.dictionary.Dictionary

fun mapItemDetailDomain(input: ItemDetail, dictionary: Dictionary): ItemDetailUIModel {
    return ItemDetailUIModel(
        dictionary,
        input.item.title,
        input.item.subtitle,
        input.description.plainText,
        input.item.price,
        input.item.originalPrice,
        input.item.soldQuantity,
        input.item.condition,
        input.item.thumbnail,
        input.hasFulfillment(),
        input.hasStorePickUp(),
        input.hasFreeShipping(),
        input.hasShippingGuaranteed(),
        input.item.warranty,
        input.item.tags,
        input.item.pictures.map { mapPictureDomain(it) },
        input.review,
        input.item.attributes
    )
}

fun mapPictureDomain(input: ItemDetail.Item.Picture): String {
    return input.url
}