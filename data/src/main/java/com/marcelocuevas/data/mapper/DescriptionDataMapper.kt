package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.DescriptionsResponse
import model.detail.Description

fun mapDescriptionDto(input: DescriptionsResponse): Description {
    return Description(
        input.text.orEmpty(),
        input.plainText.orEmpty()
    )
}