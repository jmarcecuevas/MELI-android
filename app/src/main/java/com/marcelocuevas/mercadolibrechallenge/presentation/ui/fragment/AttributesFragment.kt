package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.AttributesAdapter
import kotlinx.android.synthetic.main.fragment_attributes.*

class AttributesFragment: BaseFragment() {

    private val attributes: Array<ItemDetailUIModel.Attribute>
        get() = AttributesFragmentArgs.
            fromBundle(requireArguments()).attributesList

    override fun layoutRes(): Int = R.layout.fragment_attributes

    override fun init() {
        setupNav(toolbar)

        attributesRecyclerView.layoutManager = LinearLayoutManager(context)
        attributesRecyclerView.adapter = AttributesAdapter(context, attributes.toList())
    }
}