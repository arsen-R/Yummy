package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(
    @SerialName("created_at")
    val created_at: Int? = null,
    @SerialName("display_plural")
    val display_plural: String? = null,
    @SerialName("display_singular")
    val display_singular: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("updated_at")
    val updated_at: Int? = null,
)