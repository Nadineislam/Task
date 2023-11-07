package com.example.taskproject.showing_items_feature.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.taskproject.showing_items_feature.data.remote.dto.CombinedResponse
import com.example.taskproject.showing_items_feature.domain.repository.ItemsRepository
import retrofit2.Response
import java.time.Duration
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
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
    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDate(createdAt: String?): String {
        if (createdAt.isNullOrBlank()) {
            return "Unknown"
        }
        val dateFormatter = DateTimeFormatter.ISO_DATE_TIME
        val createdDate = OffsetDateTime.parse(createdAt, dateFormatter)
        val now = OffsetDateTime.now()
        val difference = Duration.between(createdDate, now)

        return when {
            difference.toDays() >= 180 -> {
                return createdDate.format(DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy", Locale.getDefault()))
            }
            difference.toDays() >= 120 -> "4 months ago"
            else -> {
                val months = difference.toDays() / 30
                val years = months / 12
                return "$months months ago, $years years ago"
            }
        }

}}

