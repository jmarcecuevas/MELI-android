package com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcelocuevas.mercadolibrechallenge.R
import kotlinx.android.synthetic.main.view_record_item.view.*
import model.SearchModel
import timber.log.Timber

class RecordsAdapter(val onClick: (String) -> Unit): RecyclerView.Adapter<RecordsAdapter.RecordViewHolder>() {

    private var items: List<SearchModel> = emptyList()

    fun loadItems(newItems: List<SearchModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_record_item, parent, false)
        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            Timber.d("Record in position $position clicked")
            onClick(items[position].query)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RecordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: SearchModel) {
            itemView.valueTextView.text = item.query
        }
    }

}