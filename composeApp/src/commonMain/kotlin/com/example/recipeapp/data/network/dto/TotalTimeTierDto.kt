package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalTimeTierDto(
    @SerialName("display_tier")
    val display_tier: String? = null,
    @SerialName("tier")
    val tier: String? = null,
)