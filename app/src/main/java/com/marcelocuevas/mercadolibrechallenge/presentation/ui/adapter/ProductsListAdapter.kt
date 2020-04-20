package com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.model.Product
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.inflate
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.visibleOrGone
import kotlinx.android.synthetic.main.view_product_item.view.*

class ProductsListAdapter(val onClick: (String) -> Unit): RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private var items: List<Product> = emptyList()

    fun loadItems(newItems: List<Product>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_product_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            onClick(items[position].id)
        }
    }

    override fun getItemCount() = items.size


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Product) {
            with(item) {
                Glide.with(itemView)
                    .load(imageURL)
                    .into(itemView.photoImageView)

                itemView.titleTextView.text = title
                itemView.priceTextView.text = priceLabel()

                itemView.shippingGuaranteedTextView.visibleOrGone(hasShippingGuaranteed)
                itemView.shippingGuaranteedTextView.text = shippingGuaranteedLabel()

                itemView.freeShippingTextView.visibleOrGone(freeShippingLabel().isNotEmpty())
                itemView.freeShippingTextView.text = freeShippingLabel()

                itemView.sellerTextView.visibleOrGone(sellerLabel().isNotEmpty())
                itemView.sellerTextView.text = sellerLabel()

                itemView.fulfillmentImageView.visibleOrGone(hasFulFillment)
                itemView.fulfillmentTextView.visibleOrGone(hasFulFillment)
                itemView.fulfillmentTextView.text = fulFillmentLabel()
            }
        }
    }
}