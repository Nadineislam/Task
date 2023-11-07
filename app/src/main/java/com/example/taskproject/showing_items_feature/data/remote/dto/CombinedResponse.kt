package com.example.taskproject.showing_items_feature.data.remote.dto

data class CombinedResponse(
    val item: ItemsResponseItem,
    val date: ItemsDateResponse,
    val formattedDate: String?=""
)
