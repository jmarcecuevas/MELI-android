package com.marcelocuevas.mercadolibrechallenge.presentation.model

import com.marcelocuevas.mercadolibrechallenge.presentation.utils.toPrettifiedPrice
import model.detail.ItemDetail
import model.detail.Review
import model.dictionary.Dictionary

data class ItemDetail(
    val dictionary: Dictionary,
    val title: String,
    val subtitle: String,
    val description: String,
    private val price: Double,
    private val originalPrice: Double,
    val soldQuantity: Int,
    private val condition: String,
    val thumbnail: String,
    val hasFulfillment: Boolean,
    val hasStorePickUp: Boolean,
    val hasFreeShipping: Boolean,
    val hasShippingGuaranteed: Boolean,
    val warranty: String,
    val tags: List<String>,
    val pictures: List<String>,
    val review: Review,
    val attributes: List<ItemDetail.Item.Attribute>
) {

    fun headerTitle(): String {
        if (condition().isNotEmpty() && hasReviews())
            return "${condition()}  |  ${review.reviews.size} ${dictionary.getString(SOLD)}"
        return EMPTY_STRING
    }

    fun condition(): String {
        return when(condition) {
            NEW -> dictionary.getString(NEW_LABEL)
            USED -> dictionary.getString(USED_LABEL)
            else -> EMPTY_STRING
        }
    }

    fun reviewsAmountLabel() = "(${review.reviews.size})"

    fun hasReviews(): Boolean = review.reviews.isNotEmpty()

    fun priceLabel(): String = price.toPrettifiedPrice()

    fun originalPrice(): String = originalPrice.toPrettifiedPrice()

    private fun hasZeroPrice(price: Double) = price == 0.0

    fun originalPriceIsZero() = hasZeroPrice(originalPrice)

    fun shippingGuaranteedLabel(): String = if (hasShippingGuaranteed)
        dictionary.getString(GUARANTEED_SHIPPING) else EMPTY_STRING

    companion object{
        private const val EMPTY_STRING = ""
        private const val NEW = "new"
        private const val USED = "used"
        private const val NEW_LABEL = "dictionary_new_label"
        private const val USED_LABEL = "dictionary_used_label"
        private const val GUARANTEED_SHIPPING = "dictionary_guaranteed_shipping"
        private const val SOLD = "dictionary_sold_label"
    }
}