package model.detail

class ItemDetail(
    val item: Item,
    val description: Description,
    val review: Review
){

    data class Item(
        val title: String,
        val subtitle: String,
        val price: Double,
        val soldQuantity: Int,
        val condition: String,
        val thumbnail: String,
        val shipping: model.Item.Shipping,
        val pictures: List<Picture>,
        val warranty: String,
        val attributes: List<Attribute>,
        val tags: List<String>
    ){

        data class Picture(
            val url: String,
            val size: String
        )

        data class Attribute(
            val name: String,
            val valueName: String
        )
    }
}
