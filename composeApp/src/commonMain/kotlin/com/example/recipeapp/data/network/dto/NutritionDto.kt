package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutritionDto(
    @SerialName("calories")
    val calories: Int? = null,
    @SerialName("carbohydrates")
    val carbohydrates: Int? = null,
    @SerialName("fat")
    val fat: Int? = null,
    @SerialName("fiber")
    val fiber: Int? = null,
    @SerialName("protein")
    val protein: Int? = null,
    @SerialName("sugar")
    val sugar: Int? = null,
    @SerialName("updated_at")
    val updated_at: String? = null,
)