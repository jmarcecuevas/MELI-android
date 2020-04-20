package com.marcelocuevas.data.model

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    val id: String,
    @SerializedName("site_id")
    val siteID: String,
    val title: String,
    val subtitle: String?,
    val price: Double,
    @SerializedName("original_price")
    val originalPrice: Double?,
    @SerializedName("sold_quantity")
    val soldQuantity: Int?,
    val condition: String?,
    val thumbnail: String?,
    val shipping: ShippingResponse?,
    val pictures: List<PictureResponse> = emptyList(),
    val warranty: String?,
    val attributes: List<AttributeResponse>? = emptyList(),
    val tags: List<String>? = emptyList()
)

data class PictureResponse(
    @SerializedName("secure_url")
    val url: String,
    val size: String?
)

data class AttributeResponse(
    val name: String,
    @SerializedName("value_name")
    val valueName: String
)