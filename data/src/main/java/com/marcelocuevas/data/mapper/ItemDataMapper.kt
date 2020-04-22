package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.ItemResponse
import com.marcelocuevas.data.model.SellerResponse
import com.marcelocuevas.data.model.ShippingResponse
import model.ItemModel

fun mapItemDto(input: ItemResponse): ItemModel {
    return ItemModel(
        input.id.orEmpty(),
        input.title.orEmpty(),
        input.thumbnail.orEmpty(),
        input.price.orZero(),
        input.condition.orEmpty(),
        mapSellerDto(input.seller),
        mapShippingDto(input.shipping),
        mapNullInputList(input.tags) { tagsDto ->
            tagsDto.orEmpty()
        }
    )
}

fun mapShippingDto(input: ShippingResponse?): ItemModel.Shipping {
    return ItemModel.Shipping(
        input?.freeShipping.orFalse(),
        input?.logisticType.orEmpty(),
        input?.storePickUp.orFalse()
    )
}

fun mapSellerDto(input: SellerResponse?): ItemModel.Seller {
    return ItemModel.Seller(
        input?.eshop?.nickName.orEmpty()
    )
}

fun Boolean?.orFalse(): Boolean = this ?: false

fun Double?.orZero(): Double = this ?: 0.0

fun Int?.orZero(): Int = this ?: 0

fun Float?.orZero(): Float = this ?: 0f