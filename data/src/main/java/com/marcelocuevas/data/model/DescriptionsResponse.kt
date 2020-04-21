package com.marcelocuevas.data.model

import com.google.gson.annotations.SerializedName

data class DescriptionsResponse(
    val id: String?,
    val text: String?,
    @SerializedName("plain_text")
    val plainText: String?
)
