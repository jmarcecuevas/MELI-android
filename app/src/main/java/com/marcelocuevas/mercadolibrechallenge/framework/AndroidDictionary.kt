package com.marcelocuevas.mercadolibrechallenge.framework

import android.content.Context
import com.marcelocuevas.domain.model.dictionary.Dictionary
import timber.log.Timber

class AndroidDictionary(private val context: Context): Dictionary {

    override fun getString(id: String): String {
        val resID = getResourceID(id)
        return if (resID > 0) context.getString(resID)
        else {
            Timber.w("String not found: $id")
            return ""
        }
    }

    private fun getResourceID(variableName: String): Int {
        return context.resources.getIdentifier(variableName,"string", context.packageName)
    }
}