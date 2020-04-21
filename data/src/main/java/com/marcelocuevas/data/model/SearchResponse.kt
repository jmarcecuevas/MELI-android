package com.marcelocuevas.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("site_id")
    val siteID: String?,
    val query: String?,
    val paging: PagingResponse?,
    val results: List<ItemResponse> = emptyList()
)

data class PagingResponse(
    val total: Int?,
    val offset: Int?,
    val limit: Int?
)

data class ItemResponse(
    val id: String?,
    @SerializedName("site_id")
    val siteID: String?,
    val title: String?,
    val seller: SellerResponse?,
    val price: Double?,
    @SerializedName("currency_id")
    val currencyID: String?,
    val condition: String?,
    val thumbnail: String?,
    val shipping: ShippingResponse?,
    val tags: List<String>?
)

data class SellerResponse(val eshop: EShopResponse?)

data class EShopResponse(
    @SerializedName("nick_name")
    val nickName: String?
)

data class ShippingResponse(
    @SerializedName("free_shipping")
    val freeShipping: Boolean?,
    @SerializedName("logistic_type")
    val logisticType: String?,
    @SerializedName("store_pick_up")
    val storePickUp: Boolean?
)