package com.example.taskproject.showing_items_feature.data.remote.dto

data class ItemsDateResponse(
    val id: Long,
    val name: String,
    val owner: Owner,
    val created_at: String
)
