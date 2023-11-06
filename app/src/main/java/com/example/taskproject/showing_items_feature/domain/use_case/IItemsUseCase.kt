package com.example.taskproject.showing_items_feature.domain.use_case

import com.example.taskproject.showing_items_feature.data.remote.dto.CombinedResponse
import retrofit2.Response

interface IItemsUseCase {
    suspend operator fun invoke(): Response<List<CombinedResponse>>
}