package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrandDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("image_url")
    val image_url: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("slug")
    val slug: String? = null
)
