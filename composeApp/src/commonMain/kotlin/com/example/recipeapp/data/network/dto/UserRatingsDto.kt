package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRatingsDto(
    @SerialName("count_negative")
    val count_negative: Int? = null,
    @SerialName("count_positive")
    val count_positive: Int? = null,
    @SerialName("score")
    val score: Double? = null,
)