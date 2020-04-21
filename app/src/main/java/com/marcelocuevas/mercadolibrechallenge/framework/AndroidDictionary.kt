package com.marcelocuevas.mercadolibrechallenge.framework

import android.content.Context
import model.dictionary.Dictionary

class AndroidDictionary(private val context: Context): Dictionary {

    override fun getString(id: String): String {
        val resID = getResourceID(id)
        return if (resID > 0) context.getString(resID) else ""
    }

    private fun getResourceID(variableName: String): Int {
        return context.resources.getIdentifier(variableName,"string", context.packageName)
    }
}