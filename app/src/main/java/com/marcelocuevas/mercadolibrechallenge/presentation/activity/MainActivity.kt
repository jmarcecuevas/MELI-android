package com.marcelocuevas.mercadolibrechallenge.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.marcelocuevas.mercadolibrechallenge.R
import com.marcelocuevas.mercadolibrechallenge.presentation.utils.hideKeyboard

class MainActivity :
    AppCompatActivity(R.layout.activity_main),
    NavController.OnDestinationChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController().addOnDestinationChangedListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        navController().removeOnDestinationChangedListener(this)
    }

    private fun navController(): NavController =
        findNavController(R.id.fragment_host)


    override fun onDestinationChanged(controller: NavController,
                                      destination: NavDestination,
                                      arguments: Bundle?) {
        currentFocus?.hideKeyboard()
    }
}
