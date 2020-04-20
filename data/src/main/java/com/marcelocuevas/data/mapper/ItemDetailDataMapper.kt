package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.AttributeResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.PictureResponse
import model.detail.ItemDetail

fun mapItemDetailDto(input: ItemDetailResponse): ItemDetail.Item {
    return ItemDetail.Item(
        input.id.orEmpty(),
        input.subtitle.orEmpty(),
        input.price.orZero(),
        input.soldQuantity.orZero(),
        input.condition.orEmpty(),
        input.thumbnail.orEmpty(),
        mapShippingDto(input.shipping),
        mapNullInputList(input.pictures) { pictureDto ->
            mapPictureDto(pictureDto)
        },
        input.warranty.orEmpty(),
        mapNullInputList(input.attributes) { attributeDto ->
            mapAttributeDto(attributeDto)
        },
        mapNullInputList(input.tags) { tagDto ->
            tagDto.orEmpty()
        }
    )
}

fun mapPictureDto(input: PictureResponse): ItemDetail.Item.Picture {
    return ItemDetail.Item.Picture(
        input.url.orEmpty(),
        input.size.orEmpty()
    )
}

fun mapAttributeDto(input: AttributeResponse): ItemDetail.Item.Attribute {
    return ItemDetail.Item.Attribute(
        input.name.orEmpty(),
        input.valueName.orEmpty()
    )
}