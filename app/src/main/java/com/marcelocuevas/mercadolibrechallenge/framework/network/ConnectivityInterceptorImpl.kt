package com.marcelocuevas.mercadolibrechallenge.framework.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class ConnectivityInterceptorImpl(context: Context): ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline()) {
            Timber.w("No internet connection")
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager: ConnectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}