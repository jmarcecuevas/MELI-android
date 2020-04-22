package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.databinding.FragmentProductDetailBinding
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.AttributesAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.ReviewsAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.SliderAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.ItemViewModel
import com.opensooq.pluto.base.PlutoAdapter
import com.opensooq.pluto.listeners.OnItemClickListener
import com.opensooq.pluto.listeners.OnSlideChangeListener
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.fragment_product_detail.toolbar
import kotlinx.android.synthetic.main.include_attributes_product_detail.*
import kotlinx.android.synthetic.main.include_reviews_product_detail.*
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

        showAllAttrsButton.setOnClickListener {
            navigateToAttrsFragment()
        }

        showAllReviewsButton.setOnClickListener {
            navigateToReviewsFragment()
        }

        startObserving()
    }

    private fun setupBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun startObserving() {
        viewModel.item.observe(this, Observer {
            showPictures(it.pictures.toMutableList())
            showAttributes(it.attributes)
            showReviews(it.review.reviews)
        })

        viewModel.errorMessage.observe(this, Observer {

        })

        viewModel.loading.observe(this, Observer {
            //progressBar.shouldShow(it)
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

    private fun showAttributes(attributes: List<ItemDetailUIModel.Attribute>) {
        attributesRecyclerView.layoutManager = LinearLayoutManager(context)
        attributesRecyclerView.adapter = AttributesAdapter(context, attributes.take(5))
    }

    private fun showReviews(reviews: List<ItemDetailUIModel.Review.Item>) {
        reviewsRecyclerView.layoutManager = LinearLayoutManager(context)
        reviewsRecyclerView.adapter = ReviewsAdapter(context, reviews.take(3))
    }

    private fun navigateToAttrsFragment() {
        val attributes = viewModel.item.value?.attributes?.toTypedArray()
        attributes?.let {
            val directions = ProductDetailFragmentDirections.
            toAttributesFragment(it)
            navigateTo(directions) }
    }

    private fun navigateToReviewsFragment() {
        val review = viewModel.item.value?.review
        review?.let {
            val directions = ProductDetailFragmentDirections.
                toReviewsFragment(it)
            navigateTo(directions)
        }
    }
}