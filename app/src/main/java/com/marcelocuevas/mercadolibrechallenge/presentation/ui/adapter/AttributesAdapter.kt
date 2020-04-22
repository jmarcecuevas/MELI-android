package com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.databinding.ViewAttributeItemBinding
import model.detail.ItemDetail


class AttributesAdapter(
    val context: Context?,
    val items: List<ItemDetail.Item.Attribute>
) : RecyclerView.Adapter<AttributesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DataBindingUtil.inflate<ViewAttributeItemBinding>(inflater, R.layout.view_attribute_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])


    inner class ViewHolder(private val binding: ViewAttributeItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemDetail.Item.Attribute) {
            if (position % 2 == 0) {
                binding.valueNameTextView.setBackgroundColor(getColor(R.color.gray10))
                binding.valueTextView.setBackgroundColor(getColor(R.color.gray00))
            } else {
                binding.valueNameTextView.setBackgroundColor(getColor(R.color.gray00))
                binding.valueTextView.setBackgroundColor(getColor(R.color.white))
            }

            binding.attribute = item
            binding.executePendingBindings()
        }

        private fun getColor(resID: Int): Int {
            context?.let { return ContextCompat.getColor(it, resID) }
            return 0
        }
    }
}


