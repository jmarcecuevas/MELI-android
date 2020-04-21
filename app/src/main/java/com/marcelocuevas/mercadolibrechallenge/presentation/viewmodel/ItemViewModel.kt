package com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.usecases.GetItemDetail
import kotlinx.coroutines.launch
import model.Result
import model.detail.ItemDetail

class ItemViewModel(private val getItemDetail: GetItemDetail): ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val item = MutableLiveData<ItemDetail>()
    private val isLoading = MutableLiveData<Boolean>()

    val itemLiveData: LiveData<ItemDetail>
        get() = item

    val errorMessageLiveData: LiveData<String>
        get() = errorMessage

    val isLoadingLiveData: LiveData<Boolean>
        get() = isLoading


    fun itemDetail(id: String) {
        isLoading.value = true
        viewModelScope.launch {
            when (val value = getItemDetail(id)) {
                is Result.Success -> {
                    isLoading.postValue(false)
                    item.postValue(value.data)
                }
                is Result.Error -> {
                    isLoading.postValue(false)
                    errorMessage.postValue(value.message.message)
                }
            }
        }
    }
}