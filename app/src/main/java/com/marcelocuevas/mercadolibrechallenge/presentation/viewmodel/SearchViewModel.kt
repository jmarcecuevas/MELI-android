package com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.domain.model.SearchProductDTO
import com.marcelocuevas.usecases.SearchProducts
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchProducts: SearchProducts
): ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val products = MutableLiveData<SearchProductDTO>()

    val productsLiveData: LiveData<SearchProductDTO>
        get() = products

    val errorMessageLiveData: LiveData<String>
        get() = errorMessage

    fun search(query: String) {
        viewModelScope.launch {
            when (val value: Result<SearchProductDTO> = searchProducts(query)) {
                is Result.Success -> products.postValue(value.data)
                is Result.Error -> errorMessage.postValue(value.message.message)
            }
        }
    }




}