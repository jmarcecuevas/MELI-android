package model.detail

data class Review(
    val ratingAverage: Float,
    val levels: Level,
    val reviews: List<Item>
){
    data class Item(
        val title: String,
        val content: String,
        val rate: Int,
        val valorization: Int,
        val likes: Int,
        val dislikes: Int,
        val revelance: Int
    )

    data class Level(
        val oneStar: Int,
        val twoStar: Int,
        val threeStar: Int,
        val fourStar: Int,
        val fiveStar: Int
    )
}
