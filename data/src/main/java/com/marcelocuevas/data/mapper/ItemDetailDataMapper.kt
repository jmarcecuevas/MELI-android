package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.AttributeResponse
import com.marcelocuevas.data.model.ItemDetailResponse
import com.marcelocuevas.data.model.PictureResponse
import com.marcelocuevas.domain.model.detail.ItemDetailModel

fun mapItemDetailDto(input: ItemDetailResponse): ItemDetailModel.ItemModel {
    return ItemDetailModel.ItemModel(
        input.id.orEmpty(),
        input.title.orEmpty(),
        input.subtitle.orEmpty(),
        input.price.orZero(),
        input.originalPrice.orZero(),
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

fun mapPictureDto(input: PictureResponse): ItemDetailModel.ItemModel.Picture {
    return ItemDetailModel.ItemModel.Picture(
        input.url.orEmpty(),
        input.size.orEmpty()
    )
}

fun mapAttributeDto(input: AttributeResponse): ItemDetailModel.ItemModel.Attribute {
    return ItemDetailModel.ItemModel.Attribute(
        input.name.orEmpty(),
        input.valueName.orEmpty()
    )
}