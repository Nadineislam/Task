package com.example.taskproject.showing_items_feature.data.remote.dto

data class ItemsResponseItem(
    val id: Int,
    val name: String,
    val owner: Owner,
    val url: String
)