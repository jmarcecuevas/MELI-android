package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.DescriptionsResponse
import com.marcelocuevas.domain.model.detail.DescriptionModel

fun mapDescriptionDto(input: DescriptionsResponse): DescriptionModel {
    return DescriptionModel(
        input.text.orEmpty(),
        input.plainText.orEmpty()
    )
}