package com.example.taskproject.showing_items_feature.domain.use_case

import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsResponse
import com.example.taskproject.showing_items_feature.domain.repository.ItemsRepository
import retrofit2.Response
import javax.inject.Inject

class ItemsUseCase @Inject constructor(private val repository: ItemsRepository) {
    suspend operator fun invoke(): Response<ItemsResponse> {
        return repository.getItems()
    }
}