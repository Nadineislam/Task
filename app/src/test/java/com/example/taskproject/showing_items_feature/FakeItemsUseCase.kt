package com.example.taskproject.showing_items_feature

import com.example.taskproject.showing_items_feature.data.remote.dto.CombinedResponse
import com.example.taskproject.showing_items_feature.domain.repository.ItemsRepository
import com.example.taskproject.showing_items_feature.domain.use_case.IItemsUseCase
import retrofit2.Response
import javax.inject.Inject

class FakeItemsUseCase @Inject constructor(private val repository: ItemsRepository) :
    IItemsUseCase {
    override suspend fun invoke(): Response<List<CombinedResponse>> {
        val itemsResponse = repository.getItems()
        val combinedResponses = itemsResponse.body()?.mapNotNull { item ->
            val dateResponse = repository.getItemsDate(item.owner.login, item.name).body()
            dateResponse?.let { CombinedResponse(item, it) }
        }
        return Response.success(combinedResponses)
    }

}