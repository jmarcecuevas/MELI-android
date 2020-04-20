package com.marcelocuevas.data.model

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    val paging: PagingResponse?,
    val reviews: List<ReviewItemResponse>?,
    @SerializedName("rating_average")
    val ratingAverage: Double?,
    @SerializedName("rating_levels")
    val ratingLevels: RatingLevelsResponse?
)

data class ReviewItemResponse(
    val title: String?,
    val content: String?,
    val rate: Int?,
    val valorization: Int?,
    val likes: Int?,
    val dislikes: Int?,
    val revelance: Int?
)

data class RatingLevelsResponse(
    @SerializedName("one_star")
    val oneStar: Int?,
    @SerializedName("two_star")
    val twoStar: Int?,
    @SerializedName("three_star")
    val threeStar: Int?,
    @SerializedName("four_star")
    val fourStar: Int?,
    @SerializedName("five_star")
    val fiveStar: Int?
)
