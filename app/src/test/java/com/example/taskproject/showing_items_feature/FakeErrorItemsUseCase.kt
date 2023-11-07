package com.example.taskproject.showing_items_feature

import com.example.taskproject.showing_items_feature.data.remote.dto.CombinedResponse
import com.example.taskproject.showing_items_feature.domain.use_case.IItemsUseCase
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeErrorItemsUseCase :IItemsUseCase{
    override suspend fun invoke(): Response<List<CombinedResponse>> {
        return Response.error(
            500,
            "Error retrieving items".toResponseBody("application/json".toMediaTypeOrNull())
        )
    }

}