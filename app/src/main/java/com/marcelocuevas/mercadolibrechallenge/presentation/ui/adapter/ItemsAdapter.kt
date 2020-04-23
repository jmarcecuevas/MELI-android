package com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.databinding.ViewProductItemBinding
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemUIModel
import kotlinx.android.synthetic.main.view_product_item.view.*
import timber.log.Timber

class ItemsAdapter(val onClick: (String) -> Unit): RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private var items: List<ItemUIModel> = emptyList()

    fun loadItems(newItems: List<ItemUIModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<ViewProductItemBinding>(inflater, R.layout.view_product_item,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            Timber.d("Item in position $position clicked")
            onClick(items[position].id)
        }
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ViewProductItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemUIModel) {
            Glide.with(itemView).load(item.imageURL).into(itemView.photoImageView)

            binding.item = item
            binding.executePendingBindings()
        }
    }
}