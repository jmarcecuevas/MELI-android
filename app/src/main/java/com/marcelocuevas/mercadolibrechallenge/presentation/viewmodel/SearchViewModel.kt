package com.marcelocuevas.mercadolibrechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.usecases.GetSearches
import com.marcelocuevas.usecases.SaveSearch
import kotlinx.coroutines.launch
import model.SearchModel

class SearchViewModel(
    private val saveSearch: SaveSearch,
    private val getSearches: GetSearches
): ViewModel() {

    private val _searches = MutableLiveData<List<SearchModel>>()

    val searches: LiveData<List<SearchModel>>
        get() = _searches

    fun onStart() {
        getAllSearches()
    }

    private fun getAllSearches() =
        viewModelScope.launch { _searches.postValue(getSearches()) }


    fun searchButtonClicked(query: String) =
        viewModelScope.launch { saveSearch(query) }
}