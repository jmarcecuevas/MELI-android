package com.marcelocuevas.mercadolibrechallenge.framework.network

import java.io.IOException

class NoConnectivityException: IOException() {

    override val message: String?
        get() = "No internet connection"
}