package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.*
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class ItemDetailDataMapperTest {

    @Test
    fun `mapItemDetailDto should replace null values with defaults`() {
        val dto = makeItemDetailResponse()
        val actual = mapItemDetailDto(dto)

        assertThat(actual.id).isEmpty()
        assertThat(actual.title).isEmpty()
        assertThat(actual.attributes).isEmpty()
        assertThat(actual.condition).isEmpty()
        assertThat(actual.soldQuantity).isEqualTo(0)
        assertThat(actual.originalPrice).isZero()
        assertThat(actual.pictures).isEmpty()
        assertThat(actual.shipping).isNotNull()
        assertThat(actual.warranty).isEmpty()
        assertThat(actual.thumbnail).isEmpty()
        assertThat(actual.price).isZero()
        assertThat(actual.subtitle).isEmpty()
    }

    @Test
    fun `mapItemDetailDto should replace with correct values`() {
        val dto = makeItemDetailResponse("id", "siteID", "title",
            "subtitle", 3455.0, 4567.0,
            24, "new", "thumbnail", null, null,
            "warrantly", emptyList(), emptyList())

        val actual = mapItemDetailDto(dto)

        assertThat(actual.id).isEqualTo("id")
        assertThat(actual.title).isEqualTo("title")
        assertThat(actual.subtitle).isEqualTo("subtitle")
        assertThat(actual.price).isEqualTo(3455.0)
        assertThat(actual.originalPrice).isEqualTo(4567.0)
        assertThat(actual.soldQuantity).isEqualTo(24)
        assertThat(actual.condition).isEqualTo("new")
        assertThat(actual.thumbnail).isEqualTo("thumbnail")
        assertThat(actual.shipping).isNotNull()
        assertThat(actual.pictures).isEmpty()
        assertThat(actual.warranty).isEqualTo("warrantly")
        assertThat(actual.tags).isEmpty()
        assertThat(actual.attributes).isEmpty()
    }

    @Test
    fun `mapPictureDto should replace null values with defaults`() {
        val dto = makePictureResponse()
        val actual = mapPictureDto(dto)

        assertThat(actual.url).isEmpty()
        assertThat(actual.size).isEmpty()
    }

    @Test
    fun `mapShippingDto should replace with correct values`() {
        val dto = makePictureResponse("url", "size")
        val actual = mapPictureDto(dto)

        assertThat(actual.url).isEqualTo("url")
    }

    @Test
    fun `mapAttributeDto should replace null values with defaults`() {
        val dto = makeAttributeResponse()
        val actual = mapAttributeDto(dto)

        assertThat(actual.name).isEmpty()
        assertThat(actual.valueName).isEmpty()
    }

    @Test
    fun `mapSellerDto should replace with correct values`() {
        val dto = makeAttributeResponse(
            "name", "valuename")
        val actual = mapAttributeDto(dto)

        assertThat(actual.name).isEqualTo("name")
        assertThat(actual.valueName).isEqualTo("valuename")
    }

    private fun makeItemDetailResponse(id: String? = null, siteID: String? = null,
                                 title: String? = null, subtitle: String? = null,
                                 price: Double? = null, originalPrice: Double? = null,
                                 soldQuantity: Int? = null, condition: String? = null,
                                 thumbnail: String? = null, shipping: ShippingResponse? = null,
                                 pictures: List<PictureResponse>? = null, warrantly: String? = null,
                                 attributes: List<AttributeResponse>? = null,
                                 tags: List<String>? = null): ItemDetailResponse {

        return ItemDetailResponse(id, siteID, title, subtitle, price, originalPrice, soldQuantity,
            condition, thumbnail, shipping, pictures, warrantly, attributes, tags)
    }

    private fun makePictureResponse(url: String? = null,
                                    size: String? = null): PictureResponse {
        return PictureResponse(url, size)
    }

    private fun makeAttributeResponse(name: String? = null,
                                      valueName: String? = null): AttributeResponse {
        return AttributeResponse(name, valueName)
    }
}