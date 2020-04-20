package com.marcelocuevas.mercadolibrechallenge.presentation.model

import java.text.NumberFormat
import java.util.*
import model.Item as DomainProduct

data class Product(
    val id: String,
    val title: String,
    val imageURL: String,
    private val price: Double,
    private val sellerLabel: String,
    val hasFreeShipping: Boolean,
    val hasShippingGuaranteed: Boolean,
    val hasFulFillment: Boolean
) {

    fun freeShippingLabel(): String {
        return if (hasFreeShipping) "Envío gratis" else ""
    }

    fun shippingGuaranteedLabel(): String {
        return if (hasShippingGuaranteed) "Envío con normalidad" else ""
    }

    fun sellerLabel(): String {
        return if (sellerLabel.isNotEmpty()) "Vendido por $sellerLabel" else ""
    }

    fun fulFillmentLabel(): String {
        return "FULL"
    }

    fun priceLabel(): String {
        val nf = NumberFormat.getNumberInstance(Locale.GERMAN)
        nf.isGroupingUsed = true
        return "$ ${nf.format(price)}"
    }
}

fun DomainProduct.mapToPresentation(): Product = Product(
    id,
    title,
    imageURL,
    price,
    sellerNickname(),
    hasFreeShipping(),
    hasShippingGuaranteed(),
    hasFulfillment()
)


