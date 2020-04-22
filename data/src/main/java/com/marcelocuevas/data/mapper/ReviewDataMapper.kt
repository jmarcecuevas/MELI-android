package com.marcelocuevas.data.mapper

import com.marcelocuevas.data.model.RatingLevelsResponse
import com.marcelocuevas.data.model.ReviewItemResponse
import com.marcelocuevas.data.model.ReviewsResponse
import model.detail.ReviewModel

fun mapReviewDto(input: ReviewsResponse): ReviewModel {
    return ReviewModel(
        input.ratingAverage.orZero(),
        mapLevelsDto(input.ratingLevels),
        mapNullInputList(input.reviews) { reviewDto ->
            mapReviewItemDto(reviewDto)
        }
    )
}

fun mapLevelsDto(input: RatingLevelsResponse?): ReviewModel.LevelModel {
    return ReviewModel.LevelModel(
        input?.oneStar.orZero(),
        input?.twoStar.orZero(),
        input?.threeStar.orZero(),
        input?.fourStar.orZero(),
        input?.fiveStar.orZero()
    )
}

fun mapReviewItemDto(input: ReviewItemResponse): ReviewModel.ItemModel {
    return ReviewModel.ItemModel(
        input.title.orEmpty(),
        input.content.orEmpty(),
        input.rate.orZero(),
        input.valorization.orZero(),
        input.likes.orZero(),
        input.dislikes.orZero(),
        input.revelance.orZero()
    )
}