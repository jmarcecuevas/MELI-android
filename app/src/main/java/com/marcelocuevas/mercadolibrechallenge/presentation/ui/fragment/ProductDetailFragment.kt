package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.databinding.FragmentProductDetailBinding
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.AttributesAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.SliderAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.shouldShow
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.ItemViewModel
import com.opensooq.pluto.base.PlutoAdapter
import com.opensooq.pluto.listeners.OnItemClickListener
import com.opensooq.pluto.listeners.OnSlideChangeListener
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.fragment_product_detail.progressBar
import kotlinx.android.synthetic.main.fragment_product_detail.toolbar
import kotlinx.android.synthetic.main.include_attributes_product_detail.*
import model.detail.ItemDetail
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductDetailFragment: DataBindingFragment<FragmentProductDetailBinding>() {

    private val viewModel by viewModel<ItemViewModel>()

    private val id: String
        get() = ProductDetailFragmentArgs.fromBundle(requireArguments()).id

    override fun layoutRes(): Int = R.layout.fragment_product_detail

    override fun init() {
        setupNav(toolbar)
        setupBinding()
        viewModel.itemDetail(id)
        startObserving()
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun startObserving() {
        viewModel.itemLiveData.observe(this, Observer {
            showPictures(it.pictures.toMutableList())
            showAttributes(it.attributes)
        })

        viewModel.errorMessageLiveData.observe(this, Observer {

        })

        viewModel.isLoadingLiveData.observe(this, Observer {
            progressBar.shouldShow(it)
        })
    }

    private fun showPictures(pictures: MutableList<String>) {
        val adapter = SliderAdapter(pictures, object : OnItemClickListener<String> {
            override fun onItemClicked(item: String?, position: Int) {

            }
        })

        picturesSlider.setOnSlideChangeListener(object : OnSlideChangeListener {
            override fun onSlideChange(adapter: PlutoAdapter<*, *>, position: Int) {

            }
        })
        picturesSlider.create(adapter, lifecycle = lifecycle)
    }

    private fun showAttributes(attributes: List<ItemDetail.Item.Attribute>) {
        attributesRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = AttributesAdapter(context)
        attributesRecyclerView.adapter = adapter
        adapter.loadItems(attributes)
    }
}