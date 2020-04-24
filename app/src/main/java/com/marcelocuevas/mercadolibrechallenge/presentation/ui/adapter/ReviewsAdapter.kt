package com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.databinding.ViewReviewItemBinding
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel

class ReviewsAdapter(
    val context: Context?,
    private val items: List<ItemDetailUIModel.Review.Item>
) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<ViewReviewItemBinding>(inflater,
                                        R.layout.view_review_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])


    inner class ViewHolder(private val binding: ViewReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemUIModel: ItemDetailUIModel.Review.Item) {
            binding.review = itemUIModel
            binding.executePendingBindings()
        }
    }
}
