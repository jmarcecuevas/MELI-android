package com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.mercadolibrechallenge.presentation.model.Product
import model.Result
import com.marcelocuevas.usecases.SearchProducts
import kotlinx.coroutines.launch
import model.Item
import model.dictionary.Dictionary

class SearchViewModel(
    private val searchProducts: SearchProducts,
    private val dictionary: Dictionary,
    private val mapItemDomain: (Item,Dictionary) -> (Product)
): ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val products = MutableLiveData<List<Product>>()
    private val isLoading = MutableLiveData<Boolean>()

    val productsLiveData: LiveData<List<Product>>
        get() = products

    val errorMessageLiveData: LiveData<String>
        get() = errorMessage

    val isLoadingLiveData: LiveData<Boolean>
        get() = isLoading

    fun search(query: String) {
        isLoading.value = true
        viewModelScope.launch {
            when (val value = searchProducts(query)) {
                is Result.Success -> {
                    isLoading.postValue(false)
                    products.postValue(value.data.map { mapItemDomain(it,dictionary) })
                }

                is Result.Error -> {
                    isLoading.postValue(false)
                    errorMessage.postValue(value.message.message)
                }
            }
        }
    }
}