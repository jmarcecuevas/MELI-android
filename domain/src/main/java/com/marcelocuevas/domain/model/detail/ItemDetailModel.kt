package com.marcelocuevas.domain.model.detail


data class ItemDetailModel(
    val item: ItemModel,
    val description: DescriptionModel,
    val review: ReviewModel
){

    data class ItemModel(
        val id: String,
        val title: String,
        val subtitle: String,
        val price: Double,
        val originalPrice: Double,
        val soldQuantity: Int,
        val condition: String,
        val thumbnail: String,
        val shipping: com.marcelocuevas.domain.model.ItemModel.Shipping,
        val pictures: List<Picture>,
        val warranty: String,
        val attributes: List<Attribute>,
        val tags: List<String>
    ){

        data class Picture(
            val url: String,
            val size: String
        )

        data class Attribute(
            val name: String,
            val valueName: String
        )
    }

    fun hasReviews(): Boolean = review.reviews.isNotEmpty()

    fun hasRating(): Boolean = review.ratingAverage != 0f

    fun hasAttributes(): Boolean = item.attributes.isNotEmpty()

    fun hasStorePickUp(): Boolean = item.shipping.storePickUp

    fun hasFreeShipping(): Boolean = item.shipping.freeShipping

    fun hasFulfillment(): Boolean = item.shipping.logisticType == "fulfillment"

    fun hasShippingGuaranteed(): Boolean = item.tags.contains("shipping_guaranteed")
}
