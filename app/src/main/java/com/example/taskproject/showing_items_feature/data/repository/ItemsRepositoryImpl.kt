package com.example.taskproject.showing_items_feature.data.repository

import com.example.taskproject.showing_items_feature.data.remote.ItemsApi
import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsResponse
import com.example.taskproject.showing_items_feature.domain.repository.ItemsRepository
import retrofit2.Response
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(private val itemsApi: ItemsApi) : ItemsRepository {
    override suspend fun getItems(): Response<ItemsResponse> = itemsApi.getItems()
    override suspend fun getItemsDate(
        owner: String,
        repo: String
    ) = itemsApi.getItemsDate(owner, repo)
}