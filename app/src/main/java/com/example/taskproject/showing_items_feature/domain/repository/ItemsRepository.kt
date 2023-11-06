package com.example.taskproject.showing_items_feature.domain.repository

import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsDateResponse
import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsResponse
import retrofit2.Response

interface ItemsRepository {
    suspend fun getItems(): Response<ItemsResponse>
    suspend fun getItemsDate(owner: String, repo: String): Response<ItemsDateResponse>
}