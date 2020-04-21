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
        val logisticType: String,
        val storePickUp: Boolean
    )

    fun hasStorePickUp(): Boolean = shipping.storePickUp

    fun hasFreeShipping(): Boolean = shipping.freeShipping

    fun sellerNickname(): String = seller.nickname

    fun hasFulfillment(): Boolean = shipping.logisticType == "fulfillment"

    fun hasShippingGuaranteed(): Boolean = tags.contains("shipping_guaranteed")
}