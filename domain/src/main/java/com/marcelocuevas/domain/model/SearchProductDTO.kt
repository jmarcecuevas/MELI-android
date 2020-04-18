package com.marcelocuevas.domain.model

import com.google.gson.annotations.SerializedName

data class SearchProductDTO(
    @SerializedName("site_id")
    val siteID: String,
    val query: String,
    val paging: PagingDTO,
    val results: List<ProductDTO> = emptyList()
)

data class PagingDTO(
    val total: Int,
    val offset: Int,
    val limit: Int
)

data class ProductDTO(
    val id: String,
    @SerializedName("site_id")
    val siteID: String,
    val title: String,
    val price: Double,
    @SerializedName("currency_id")
    val currencyID: String,
    val condition: String,
    val thumbnail: String,
    val shipping: ShippingDTO
)

data class ShippingDTO(
    @SerializedName("free_shipping")
    val freeShipping: Boolean
)