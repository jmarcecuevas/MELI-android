package com.marcelocuevas.mercadolibrechallenge.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

abstract class DataBindingFragment<T: ViewDataBinding>: Fragment() {

    lateinit var binding: T

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    protected fun setupNav(toolbar: Toolbar) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    protected fun navigateTo(resId: Int) {
        findNavController().navigate(resId)
    }

    protected fun navigateTo(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    abstract fun layoutRes(): Int

    abstract fun init()
}