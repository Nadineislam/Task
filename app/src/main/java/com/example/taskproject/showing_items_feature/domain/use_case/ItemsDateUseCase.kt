package com.example.taskproject.showing_items_feature.domain.use_case

import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsDateResponse
import com.example.taskproject.showing_items_feature.domain.repository.ItemsRepository
import retrofit2.Response
import javax.inject.Inject

class ItemsDateUseCase @Inject constructor(private val repository: ItemsRepository) {
    suspend operator fun invoke(owner: String, repo: String): Response<ItemsDateResponse> =
        repository.getItemsDate(owner, repo)
}