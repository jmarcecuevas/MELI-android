package com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemUIModel
import com.marcelocuevas.domain.model.Result
import com.marcelocuevas.usecases.SearchItems
import kotlinx.coroutines.launch
import com.marcelocuevas.domain.model.ItemModel
import com.marcelocuevas.domain.model.dictionary.Dictionary

class SearchResultsViewModel(
    private val searchProducts: SearchItems,
    private val dictionary: Dictionary,
    private val mapItemDomain: (ItemModel, Dictionary) -> (ItemUIModel)
): ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    private val _items = MutableLiveData<List<ItemUIModel>>()
    private val _loading = MutableLiveData<Boolean>()

    private var queryToSearch: String = ""

    val items: LiveData<List<ItemUIModel>>
        get() = _items

    val hasError: LiveData<Boolean>
        get() = _error

    val loading: LiveData<Boolean>
        get() = _loading

    fun onStart(query: String) {
        queryToSearch = query
        search()
    }

    fun retryClicked() {
        search()
    }

    private fun search() {
        _error.value = false
        _loading.value = true
        viewModelScope.launch {
            when (val value = searchProducts(queryToSearch)) {
                is Result.Success -> {
                    _loading.postValue(false)
                    _error.postValue(false)
                    _items.postValue(value.data.map { mapItemDomain(it,dictionary) })
                }

                is Result.Error -> {
                    _loading.postValue(false)
                    _error.postValue(true)
                }
            }
        }
    }
}