package com.example.taskproject.showing_items_feature.data.remote

import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsDateResponse
import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemsApi {
    @GET("repositories")
    suspend fun getItems(): Response<ItemsResponse>

    @GET("repos/{owner}/{repo}")
    suspend fun getItemsDate(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<ItemsDateResponse>
}