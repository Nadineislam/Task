package com.example.taskproject.showing_items_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskproject.core.utils.Resource
import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsDateResponse
import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsResponse
import com.example.taskproject.showing_items_feature.domain.use_case.ItemsDateUseCase
import com.example.taskproject.showing_items_feature.domain.use_case.ItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemsUseCase: ItemsUseCase,
    private val itemsDateUseCase: ItemsDateUseCase
) : ViewModel() {
    private val _items: MutableStateFlow<Resource<ItemsResponse>> =
        MutableStateFlow(Resource.Loading())
    val items: StateFlow<Resource<ItemsResponse>> = _items
    private val _itemsDate: MutableStateFlow<Resource<ItemsDateResponse>> =
        MutableStateFlow(Resource.Loading())
    val itemsDate: StateFlow<Resource<ItemsDateResponse>> = _itemsDate

    init {
        getItems()
    }

    private fun getItems() = viewModelScope.launch {
        val response = itemsUseCase()
        _items.value = handleItemsResponse(response)
    }

    private fun handleItemsResponse(response: Response<ItemsResponse>): Resource<ItemsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error("An error occurred")
    }

    fun getItemsDate(owner: String, repo: String) = viewModelScope.launch {
        val response = itemsDateUseCase(owner, repo)
        _itemsDate.value = handleItemsDateResponse(response)

    }

    private fun handleItemsDateResponse(response: Response<ItemsDateResponse>): Resource<ItemsDateResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error("An error occurred")
    }

}

