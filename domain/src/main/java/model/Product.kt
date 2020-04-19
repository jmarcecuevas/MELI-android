package model

data class Product(
    val id: String,
    val title: String,
    val imageURL: String,
    val price: Double,
    val condition: String?,
    val seller: Seller?,
    val shipping: Shipping?,
    val tags: List<String>?
) {
    data class Seller(val nickname: String?)

    data class Shipping(
        val freeShipping: Boolean? = false,
        val logisticType: String? = ""
    )

    fun hasFreeShipping(): Boolean {
        shipping?.let { it.freeShipping?.let { return it } }
        return false
    }

    fun sellerNickname(): String {
        seller?.let { it.nickname?.let { return it } }
        return ""
    }

    fun hasFulfillment(): Boolean {
        shipping?.let { return it.logisticType == "fulfillment" }
        return false
    }

    fun hasShippingGuaranteed(): Boolean {
        tags?.let { return it.contains("shipping_guaranteed") }
        return false
    }
}