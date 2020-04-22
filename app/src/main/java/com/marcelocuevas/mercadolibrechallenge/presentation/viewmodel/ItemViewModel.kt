package com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.usecases.GetItemDetail
import kotlinx.coroutines.launch
import model.Result
import model.detail.ItemDetailModel
import model.dictionary.Dictionary
import com.marcelocuevas.mercadolibrechallenge.presentation.model.ItemDetailUIModel as ItemDetailUIModel

class ItemViewModel(
    private val getItemDetail: GetItemDetail,
    private val dictionary: Dictionary,
    private val mapItemDetailDomain: (ItemDetailModel, Dictionary) -> (ItemDetailUIModel)
): ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    private val _item = MutableLiveData<ItemDetailUIModel>()
    private val _loading = MutableLiveData<Boolean>()

    val item: LiveData<ItemDetailUIModel>
        get() = _item

    val error: LiveData<Boolean>
        get() = _error

    val loading: LiveData<Boolean>
        get() = _loading

    private var id: String = ""

    fun onStart(id: String) {
        this.id = id
        itemDetail()
    }

    fun retryClicked() {
        itemDetail()
    }

    private fun itemDetail() {
        _loading.value = true
        _error.postValue(false)
        viewModelScope.launch {
            when (val value = getItemDetail(id)) {
                is Result.Success -> {
                    _loading.postValue(false)
                    _item.postValue(mapItemDetailDomain(value.data,dictionary))
                }
                is Result.Error -> {
                    _loading.postValue(false)
                    _error.postValue(true)
                }
            }
        }
    }
}