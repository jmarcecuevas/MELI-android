package model

data class Item(
    val id: String,
    val title: String,
    val imageURL: String,
    val price: Double,
    val condition: String,
    val seller: Seller,
    val shipping: Shipping,
    val tags: List<String>
) {
    data class Seller(val nickname: String)

    data class Shipping(
        val freeShipping: Boolean,
        val logisticType: String
    )

    fun hasFreeShipping(): Boolean {
        return shipping.freeShipping
    }

    fun sellerNickname(): String {
        return seller.nickname
    }

    fun hasFulfillment(): Boolean {
        return shipping.logisticType == "fulfillment"
    }

    fun hasShippingGuaranteed(): Boolean {
        return tags.contains("shipping_guaranteed")
    }
}