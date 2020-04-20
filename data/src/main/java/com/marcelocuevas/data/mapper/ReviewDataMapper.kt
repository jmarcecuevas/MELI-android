package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.RatingLevelsResponse
import com.marcelocuevas.data.model.ReviewItemResponse
import com.marcelocuevas.data.model.ReviewsResponse
import model.detail.Review

fun mapReviewDto(input: ReviewsResponse): Review {
    return Review(
        input.ratingAverage.orZero(),
        mapLevelsDto(input.ratingLevels),
        mapNullInputList(input.reviews) { reviewDto ->
            mapReviewItemDto(reviewDto)
        }
    )
}

fun mapLevelsDto(input: RatingLevelsResponse?): Review.Level {
    return Review.Level(
        input?.oneStar.orZero(),
        input?.twoStar.orZero(),
        input?.threeStar.orZero(),
        input?.fourStar.orZero(),
        input?.fiveStar.orZero()
    )
}

fun mapReviewItemDto(input: ReviewItemResponse): Review.Item {
    return Review.Item(
        input.title.orEmpty(),
        input.content.orEmpty(),
        input.rate.orZero(),
        input.valorization.orZero(),
        input.likes.orZero(),
        input.dislikes.orZero(),
        input.revelance.orZero()
    )
}