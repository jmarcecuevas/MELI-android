package com.marcelocuevas.data.mapper

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.marcelocuevas.data.model.*

class ReviewDataMapperTest {

    @Test
    fun `mapReviewDto should replace null values with defaults`() {
        val dto = makeReviewsResponse()
        val actual = mapReviewDto(dto)

        assertThat(actual.ratingAverage).isZero()
        assertThat(actual.levels).isNotNull()
        assertThat(actual.reviews).isEmpty()
    }

    @Test
    fun `mapReviewDto should replace with correct values`() {
        val dto = makeReviewsResponse(
            null, emptyList(),3.0f,null)

        val actual = mapReviewDto(dto)

        assertThat(actual.levels).isNotNull()
        assertThat(actual.ratingAverage).isEqualTo(3.0f)
        assertThat(actual.reviews).isEmpty()
    }

    @Test
    fun `mapLevelsDto should replace null values with defaults`() {
        val dto = makeLevelsResponse()
        val actual = mapLevelsDto(dto)

        assertThat(actual.oneStar).isEqualTo(0)
        assertThat(actual.twoStar).isEqualTo(0)
        assertThat(actual.threeStar).isEqualTo(0)
        assertThat(actual.fourStar).isEqualTo(0)
        assertThat(actual.fiveStar).isEqualTo(0)
    }

    @Test
    fun `mapLevelsDto should replace with correct values`() {
        val dto = makeLevelsResponse(
            3,45,6,12,56)

        val actual = mapLevelsDto(dto)

        assertThat(actual.oneStar).isEqualTo(3)
        assertThat(actual.twoStar).isEqualTo(45)
        assertThat(actual.threeStar).isEqualTo(6)
        assertThat(actual.fourStar).isEqualTo(12)
        assertThat(actual.fiveStar).isEqualTo(56)
    }

    @Test fun `mapReviewItemDto should replace null values with defaults`() {
        val dto = makeReviewItemResponse()
        val actual = mapReviewItemDto(dto)

        assertThat(actual.title).isEmpty()
        assertThat(actual.content).isEmpty()
        assertThat(actual.rate).isEqualTo(0)
        assertThat(actual.valorization).isEqualTo(0)
        assertThat(actual.likes).isEqualTo(0)
        assertThat(actual.dislikes).isEqualTo(0)
        assertThat(actual.revelance).isEqualTo(0)
    }

    @Test
    fun `mapReviewItemDto should replace with correct values`() {
        val dto = makeReviewItemResponse("title",
            "content",5, 10, 23,4,6)

        val actual = mapReviewItemDto(dto)

        assertThat(actual.title).isEqualTo("title")
        assertThat(actual.content).isEqualTo("content")
        assertThat(actual.rate).isEqualTo(5)
        assertThat(actual.valorization).isEqualTo(10)
        assertThat(actual.likes).isEqualTo(23)
        assertThat(actual.dislikes).isEqualTo(4)
        assertThat(actual.revelance).isEqualTo(6)
    }

    private fun makeReviewsResponse(paging: PagingResponse? = null,
                                    reviews: List<ReviewItemResponse>? = null, ratingAverage: Float? = null,
                                    ratingLevels: RatingLevelsResponse? = null): ReviewsResponse {
        return ReviewsResponse(
            paging,
            reviews,
            ratingAverage,
            ratingLevels
        )
    }

    private fun makeLevelsResponse(oneStar: Int? = null, twoStar: Int? = null,
                                   threeStar: Int? = null, fourStar: Int? = null,
                                   fiveStar: Int? = null): RatingLevelsResponse {
        return RatingLevelsResponse(
            oneStar,
            twoStar,
            threeStar,
            fourStar,
            fiveStar
        )
    }

    private fun makeReviewItemResponse(title: String? = null, content: String? = null,
                                       rate: Int? = null, valorization: Int? = null,
                                       likes: Int? = null, dislikes: Int? = null,
                                       revelance: Int? = null): ReviewItemResponse {
        return ReviewItemResponse(
            title,
            content,
            rate,
            valorization,
            likes,
            dislikes,
            revelance
        )
    }
}