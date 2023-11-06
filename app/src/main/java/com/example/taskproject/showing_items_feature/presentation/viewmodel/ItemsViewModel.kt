package com.example.taskproject.showing_items_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskproject.core.utils.Resource
import com.example.taskproject.showing_items_feature.data.remote.dto.CombinedResponse
import com.example.taskproject.showing_items_feature.domain.use_case.IItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemsUseCase: IItemsUseCase
) : ViewModel() {
    private val _items: MutableStateFlow<Resource<List<CombinedResponse>>> =
        MutableStateFlow(Resource.Loading())
    val items: StateFlow<Resource<List<CombinedResponse>>> = _items

    init {
        getItems()
    }

    fun getItems() = viewModelScope.launch {
        val response = itemsUseCase()
        _items.value = handleItemsResponse(response)
    }

    private fun handleItemsResponse(response: Response<List<CombinedResponse>>): Resource<List<CombinedResponse>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error("An error occurred")
    }
}



