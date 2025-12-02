package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResultDto(
    @SerialName("count")
    val count: Int?,
    @SerialName("results")
    val results: List<RecipeDto?>?,
)