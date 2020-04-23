package com.marcelocuevas.mercadolibrechallenge.presentation.model

import android.os.Parcelable
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.toPrettifiedPrice
import kotlinx.android.parcel.Parcelize
import com.marcelocuevas.domain.model.dictionary.Dictionary

data class ItemDetailUIModel(
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
    val attributes: List<Attribute>
) {

    @Parcelize
    data class Review(
        val ratingAverage: Float,
        val levels: Level,
        val reviews: List<Item>
    ): Parcelable {

        @Parcelize
        data class Item(
            val title: String,
            val content: String,
            val rate: Int,
            val valorization: Int,
            val likes: Int,
            val dislikes: Int,
            val revelance: Int
        ): Parcelable

        @Parcelize
        data class Level(
            val oneStar: Int,
            val twoStar: Int,
            val threeStar: Int,
            val fourStar: Int,
            val fiveStar: Int
        ): Parcelable

        fun reviewsAmountLabel() = "(${reviews.size})"

        fun hasReviews(): Boolean = reviews.isNotEmpty()

        fun ratingAverageLabel() = "${ratingAverage}"
    }

    @Parcelize
    data class Attribute(val name: String, val valueName: String) : Parcelable

    fun reviewsAmountLabel() = review.reviewsAmountLabel()

    fun reviewsTitleLabel() = "${dictionary.getString(REVIEWS_ABOUT)} $title"

    fun hasReviews(): Boolean = review.hasReviews()

    fun hasAttributes(): Boolean = attributes.isNotEmpty()

    fun priceLabel(): String = price.toPrettifiedPrice()

    fun originalPrice(): String = originalPrice.toPrettifiedPrice()

    private fun hasZeroPrice(price: Double) = price == 0.0

    fun originalPriceIsZero() = hasZeroPrice(originalPrice)

    fun shippingGuaranteedLabel(): String = if (hasShippingGuaranteed)
        dictionary.getString(GUARANTEED_SHIPPING) else EMPTY_STRING

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

    fun ratingAverageLabel() = review.ratingAverageLabel()

    fun averageLabel(): String {
        return "${dictionary.getString(AVERAGE_BETWEEN)} ${review.reviews.size} ${dictionary.getString(
            REVIEWS_LABEL)}"
    }


    companion object{
        private const val EMPTY_STRING = ""
        private const val NEW = "new"
        private const val USED = "used"
        private const val REVIEWS_LABEL = "dictionary_reviews_label"
        private const val NEW_LABEL = "dictionary_new_label"
        private const val USED_LABEL = "dictionary_used_label"
        private const val GUARANTEED_SHIPPING = "dictionary_guaranteed_shipping"
        private const val SOLD = "dictionary_sold_label"
        private const val REVIEWS_ABOUT = "dictionary_reviews_about"
        private const val AVERAGE_BETWEEN = "dictionary_average_between"
    }
}