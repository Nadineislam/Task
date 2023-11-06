package com.example.taskproject.showing_items_feature.data.remote.dto

data class Owner(
    val avatar_url: String,
    val id: Int,
    val login: String,
    val site_admin: Boolean,
    val type: String,
    val url: String
)