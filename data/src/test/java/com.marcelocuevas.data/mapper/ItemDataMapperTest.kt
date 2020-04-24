package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.*
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class ItemDataMapperTest {

    @Test
    fun `mapItemDto should replace null values with defaults`() {
        val dto = makeItemResponse()
        val actual = mapItemDto(dto)

        assertThat(actual.id).isEmpty()
        assertThat(actual.condition).isEmpty()
        assertThat(actual.imageURL).isEmpty()
        assertThat(actual.price).isZero()
        assertThat(actual.seller).isNotNull()
        assertThat(actual.shipping).isNotNull()
        assertThat(actual.tags).isEmpty()
    }

    @Test
    fun `mapItemDto should replace with correct values`() {
        val dto = makeItemResponse("id",
            null, "title", null,
            3456.0,"ARS", "new",
            "thumbnail",null, emptyList())

        val actual = mapItemDto(dto)

        assertThat(actual.id).isEqualTo("id")
        assertThat(actual.title).isEqualTo("title")
        assertThat(actual.seller).isNotNull()
        assertThat(actual.price).isEqualTo(3456.0)
        assertThat(actual.condition).isEqualTo("new")
        assertThat(actual.imageURL).isEqualTo("thumbnail")
        assertThat(actual.tags).isEmpty()
    }

    @Test
    fun `mapShippingDto should replace null values with defaults`() {
        val dto = makeShippingResponse()
        val actual = mapShippingDto(dto)

        assertThat(actual.freeShipping).isFalse()
        assertThat(actual.logisticType).isEmpty()
        assertThat(actual.storePickUp).isFalse()
    }

    @Test
    fun `mapShippingDto should replace with correct values`() {
        val dto = makeShippingResponse(
            true, "fulfillment", true)

        val actual = mapShippingDto(dto)

        assertThat(actual.storePickUp).isTrue()
        assertThat(actual.logisticType).isEqualTo("fulfillment")
        assertThat(actual.freeShipping).isTrue()
    }

    @Test
    fun `mapSellerDto should replace null values with defaults`() {
        val dto = makeSellerResponse()
        val actual = mapSellerDto(dto)

        assertThat(actual.nickname).isEmpty()
    }

    @Test
    fun `mapSellerDto should replace with correct values`() {
        val dto = makeSellerResponse(
            makeEshopResponse("marcelo"))

        val actual = mapSellerDto(dto)

        assertThat(actual.nickname).isEqualTo("marcelo")
    }

    private fun makeItemResponse(id: String? = null, siteID: String? = null,
                                 title: String? = null, seller: SellerResponse? = null,
                                 price: Double? = null, currencyID: String? = null,
                                 condition: String? = null, thumbnail: String? = null,
                                 shipping: ShippingResponse? = null,
                                 tags: List<String>? = null): ItemResponse {
        return ItemResponse(id, siteID, title, seller, price, currencyID, condition,
            thumbnail,shipping, tags)
    }

    private fun makeSellerResponse(eShop: EShopResponse? = null): SellerResponse {
        return SellerResponse(eShop)
    }

    private fun makeEshopResponse(nickname: String? = null): EShopResponse {
        return EShopResponse(nickname)
    }

    private fun makeShippingResponse(freeShipping: Boolean? = null, logisticType: String? = null,
                                     storePickup: Boolean? = null): ShippingResponse {
        return ShippingResponse(freeShipping, logisticType, storePickup)

    }
}