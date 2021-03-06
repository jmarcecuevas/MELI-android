package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.databinding.FragmentItemDetailBinding
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.AttributesAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.ReviewsAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.PictureSliderAdapter
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.shouldShow
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.visibleOrGone
import com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel.ItemViewModel
import com.opensooq.pluto.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_item_detail.*
import kotlinx.android.synthetic.main.fragment_item_detail.errorView
import kotlinx.android.synthetic.main.fragment_item_detail.progressBar
import kotlinx.android.synthetic.main.fragment_item_detail.toolbar
import kotlinx.android.synthetic.main.include_attributes_product_detail.*
import kotlinx.android.synthetic.main.include_price_and_shipping_product_detail.*
import kotlinx.android.synthetic.main.include_reviews_product_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ItemDetailFragment: DataBindingFragment<FragmentItemDetailBinding>() {

    private val viewModel by viewModel<ItemViewModel>()

    private val id: String
        get() = ItemDetailFragmentArgs.fromBundle(requireArguments()).id

    override fun layoutRes(): Int = R.layout.fragment_item_detail

    override fun init() {
        setupNav(toolbar)

        setupBinding()

        viewModel.onStart(id)

        errorView.onClick = {
            viewModel.retryClicked()
        }

        showAllAttrsButton.setOnClickListener {
            navigateToAttrsFragment()
        }

        showAllReviewsButton.setOnClickListener {
            navigateToReviewsFragment()
        }

        buyButton.setOnClickListener {
            showFeatureNotImplemented()
        }

        addToCartButton.setOnClickListener {
            showFeatureNotImplemented()
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

        viewModel.error.observe(this, Observer {
            errorView.visibleOrGone(it)
            if (it) errorView.show() else errorView.hide()
        })

        viewModel.loading.observe(this, Observer {
            progressBar.shouldShow(it)
        })
    }

    private fun showPictures(pictures: MutableList<String>) {
        val adapter = PictureSliderAdapter(pictures, object : OnItemClickListener<String> {
            override fun onItemClicked(item: String?, position: Int) {}
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
            val directions = ItemDetailFragmentDirections.
            toAttributesFragment(it)
            navigateTo(directions) }
    }

    private fun navigateToReviewsFragment() {
        val review = viewModel.item.value?.review
        review?.let {
            val directions = ItemDetailFragmentDirections.
                toReviewsFragment(it)
            navigateTo(directions)
        }
    }

    private fun showFeatureNotImplemented() {
        Toast.makeText(context,
            getString(R.string.feature_not_implemented),
            Toast.LENGTH_SHORT).show()
    }
}