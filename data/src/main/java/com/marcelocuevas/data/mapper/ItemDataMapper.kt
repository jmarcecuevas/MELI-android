package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.ItemResponse
import com.marcelocuevas.data.model.SellerResponse
import com.marcelocuevas.data.model.ShippingResponse
import model.Item

fun mapItemDto(input: ItemResponse): Item {
    return Item(
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

fun mapShippingDto(input: ShippingResponse?): Item.Shipping {
    return Item.Shipping(
        input?.freeShipping.orFalse(),
        input?.logisticType.orEmpty()
    )
}

fun mapSellerDto(input: SellerResponse?): Item.Seller {
    return Item.Seller(
        input?.eshop?.nickName.orEmpty()
    )
}

fun Boolean?.orFalse(): Boolean = this ?: false

fun Double?.orZero(): Double = this ?: 0.0

fun Int?.orZero(): Int = this ?: 0

fun Float?.orZero(): Float = this ?: 0f