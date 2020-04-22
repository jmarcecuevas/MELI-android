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

    private val _errorMessage = MutableLiveData<String>()
    private val _item = MutableLiveData<ItemDetailUIModel>()
    private val _isLoading = MutableLiveData<Boolean>()

    val item: LiveData<ItemDetailUIModel>
        get() = _item

    val errorMessage: LiveData<String>
        get() = _errorMessage

    val loading: LiveData<Boolean>
        get() = _isLoading


    fun itemDetail(id: String) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val value = getItemDetail(id)) {
                is Result.Success -> {
                    _isLoading.postValue(false)
                    _item.postValue(mapItemDetailDomain(value.data,dictionary))
                }
                is Result.Error -> {
                    _isLoading.postValue(false)
                    _errorMessage.postValue(value.message.message)
                }
            }
        }
    }
}