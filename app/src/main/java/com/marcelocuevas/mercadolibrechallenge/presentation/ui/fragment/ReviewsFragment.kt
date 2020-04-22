package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.databinding.FragmentReviewsBinding
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel
import com.marcelocuevas.mercadolibrechallenge.presentation.ui.adapter.ReviewsAdapter
import kotlinx.android.synthetic.main.fragment_reviews.*

class ReviewsFragment: DataBindingFragment<FragmentReviewsBinding>() {

    private val review: ItemDetailUIModel.Review
        get() = ReviewsFragmentArgs.
            fromBundle(requireArguments()).review

    override fun layoutRes(): Int = R.layout.fragment_reviews

    override fun init() {
        setupNav(toolbar)

        binding.review = review

        reviewsRecyclerView.layoutManager = LinearLayoutManager(context)
        reviewsRecyclerView.adapter = ReviewsAdapter(context, review.reviews)
    }
}