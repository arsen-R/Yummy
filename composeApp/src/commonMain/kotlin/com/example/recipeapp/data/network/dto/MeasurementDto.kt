package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeasurementDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("quantity")
    val quantity: String? = null,
    @SerialName("unit")
    val unit: UnitsDto? = null,
)