package com.example.taskproject.showing_items_feature.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.taskproject.core.utils.formatDate
import com.example.taskproject.showing_items_feature.data.remote.dto.CombinedResponse
import com.example.taskproject.showing_items_feature.domain.repository.ItemsRepository
import retrofit2.Response
import javax.inject.Inject

class ItemsUseCase @Inject constructor(private val repository: ItemsRepository) : IItemsUseCase {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend operator fun invoke(): Response<List<CombinedResponse>> {
        val itemsResponse = repository.getItems()
        val combinedResponses = itemsResponse.body()?.mapNotNull { item ->
            val dateResponse = repository.getItemsDate(item.owner.login, item.name).body()
            val formattedDate = formatDate(dateResponse?.created_at ?: "")
            dateResponse?.let { CombinedResponse(item, it, formattedDate) }
        }
        return Response.success(combinedResponses ?: emptyList())
    }
  }

