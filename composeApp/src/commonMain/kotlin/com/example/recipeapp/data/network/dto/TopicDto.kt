package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicDto(
    @SerialName("name")
    val name: String? = null,
    @SerialName("slug")
    val slug: String? = null,
)