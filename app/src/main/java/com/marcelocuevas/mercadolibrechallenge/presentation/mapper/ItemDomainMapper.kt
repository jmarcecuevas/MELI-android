package com.marcelocuevas.mercadolibrechallenge.presentation.mapper

import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemUIModel
import model.Item
import model.dictionary.Dictionary

fun mapItemDomain(input: Item, dictionary: Dictionary): ItemUIModel {
    return ItemUIModel(
        dictionary,
        input.id,
        input.title,
        input.imageURL,
        input.price,
        input.sellerNickname(),
        input.hasFreeShipping(),
        input.hasShippingGuaranteed(),
        input.hasFulfillment()
    )
}