package com.marcelocuevas.mercadolibrechallenge.presentation.model

import com.marcelocuevas.mercadolibrechallenge.presentation.utils.toPrettifiedPrice
import com.marcelocuevas.domain.model.dictionary.Dictionary

data class ItemUIModel(
    private val dictionary: Dictionary,
    val id: String,
    val title: String,
    val imageURL: String,
    private val price: Double,
    private val sellerLabel: String,
    private val hasFreeShipping: Boolean,
    val hasShippingGuaranteed: Boolean,
    val hasFulFillment: Boolean
) {

    fun freeShippingLabel(): String =
        if (hasFreeShipping) dictionary.getString(FREE_SHIPPING) else EMPTY_STRING


    fun shippingGuaranteedLabel(): String = if (hasShippingGuaranteed)
            dictionary.getString(GUARANTEED_SHIPPING) else EMPTY_STRING


    fun sellerLabel(): String =
       if (sellerLabel.isNotEmpty())
            "${dictionary.getString(SOLD_BY)} $sellerLabel" else EMPTY_STRING


    fun fulFillmentLabel(): String = dictionary.getString(FULL)

    fun priceLabel(): String = price.toPrettifiedPrice()


    companion object{
        private const val FREE_SHIPPING = "dictionary_free_shipping"
        private const val GUARANTEED_SHIPPING = "dictionary_guaranteed_shipping"
        private const val SOLD_BY = "dictionary_sold_by"
        private const val FULL = "dictionary_full"
        private const val EMPTY_STRING = ""
    }
}


