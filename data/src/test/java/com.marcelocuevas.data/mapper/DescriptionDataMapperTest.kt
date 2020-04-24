package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.DescriptionsResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DescriptionDataMapperTest {

    private fun makeDescriptionResponse(id: String? = null, text: String? = null,
        plainText: String? = null): DescriptionsResponse{
        return DescriptionsResponse(id, text, plainText)
    }

    @Test
    fun `mapDescriptionDto should replace null values with defaults`() {
        val dto = makeDescriptionResponse()
        val actual = mapDescriptionDto(dto)

        assertThat(actual.text).isEmpty()
        assertThat(actual.plainText).isEmpty()
    }

    @Test
    fun `mapDescriptionDto should replace with correct values`() {
        val dto = makeDescriptionResponse(
            "id",
            "text",
            "plainText"
        )

        val actual = mapDescriptionDto(dto)

        assertThat(actual.text).isEqualTo("text")
        assertThat(actual.plainText).isEqualTo("plainText")
    }
}