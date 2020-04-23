package com.marcelocuevas.domain.model.detail

data class ReviewModel(
    val ratingAverage: Float,
    val levels: LevelModel,
    val reviews: List<ItemModel>
){
    data class ItemModel(
        val title: String,
        val content: String,
        val rate: Int,
        val valorization: Int,
        val likes: Int,
        val dislikes: Int,
        val revelance: Int
    )

    data class LevelModel(
        val oneStar: Int,
        val twoStar: Int,
        val threeStar: Int,
        val fourStar: Int,
        val fiveStar: Int
    )
}
