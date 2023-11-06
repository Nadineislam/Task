package com.example.taskproject.showing_items_feature

import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsDateResponse
import com.example.taskproject.showing_items_feature.data.remote.dto.ItemsResponse
import com.example.taskproject.showing_items_feature.data.remote.dto.Owner
import com.example.taskproject.showing_items_feature.domain.repository.ItemsRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response


val fakeSuccessItemsRepository = object : ItemsRepository {
    override suspend fun getItems(): Response<ItemsResponse> {
        val itemsResponse = ItemsResponse()
        return Response.success(itemsResponse)
    }

    override suspend fun getItemsDate(owner: String, repo: String): Response<ItemsDateResponse> {
        val itemsDateResponse = ItemsDateResponse(
            1, "grit", Owner(
                avatar_url = "https://avatars.githubusercontent.com/u/1?v=4",
                id = 1,
                login = "mojombo",
                site_admin = false,
                type = "User",
                url = "https://api.github.com/users/mojombo"
            ), "2023-01-01"
        )
        return Response.success(itemsDateResponse)
    }
}
val fakeErrorItemsRepository = object : ItemsRepository {
    override suspend fun getItems(): Response<ItemsResponse> {
        return Response.error(
            500,
            "Error retrieving items".toResponseBody("application/json".toMediaTypeOrNull())
        )
    }

    override suspend fun getItemsDate(owner: String, repo: String): Response<ItemsDateResponse> {
        return Response.error(
            500,
            "Error retrieving items date"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
    }
}
