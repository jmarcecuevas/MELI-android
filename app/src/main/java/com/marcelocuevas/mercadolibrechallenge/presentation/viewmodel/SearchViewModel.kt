package com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemUIModel
import model.Result
import com.marcelocuevas.usecases.SearchProducts
import kotlinx.coroutines.launch
import model.ItemModel
import model.dictionary.Dictionary

class SearchViewModel(
    private val searchProducts: SearchProducts,
    private val dictionary: Dictionary,
    private val mapItemDomain: (ItemModel, Dictionary) -> (ItemUIModel)
): ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    private val _items = MutableLiveData<List<ItemUIModel>>()
    private val _loading = MutableLiveData<Boolean>()

    val items: LiveData<List<ItemUIModel>>
        get() = _items

    val errorMessageLiveData: LiveData<String>
        get() = _errorMessage

    val isLoadingLiveData: LiveData<Boolean>
        get() = _loading

    fun search(query: String) {
        _loading.value = true
        viewModelScope.launch {
            when (val value = searchProducts(query)) {
                is Result.Success -> {
                    _loading.postValue(false)
                    _items.postValue(value.data.map { mapItemDomain(it,dictionary) })
                }

                is Result.Error -> {
                    _loading.postValue(false)
                    _errorMessage.postValue(value.message.message)
                }
            }
        }
    }
}