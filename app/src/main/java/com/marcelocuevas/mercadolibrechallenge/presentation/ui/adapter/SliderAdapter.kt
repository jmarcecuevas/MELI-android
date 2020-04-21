package com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.loadImage
import com.opensooq.pluto.base.PlutoAdapter
import com.opensooq.pluto.base.PlutoViewHolder
import com.opensooq.pluto.listeners.OnItemClickListener

class SliderAdapter(items: MutableList<String>, onItemClickListener: OnItemClickListener<String>) :
    PlutoAdapter<String, SliderAdapter.ViewHolder>(items, onItemClickListener) {

    override fun getViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent, R.layout.item_picture_slide)

    class ViewHolder(parent: ViewGroup, itemLayoutId: Int) : PlutoViewHolder<String>(parent, itemLayoutId) {

        private var imageView: ImageView =
            getView(R.id.imageView)

        override fun set(item: String, position: Int) {
            imageView.loadImage(item)
        }
    }
}