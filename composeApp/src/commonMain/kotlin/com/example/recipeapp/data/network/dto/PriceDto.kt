package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceDto(
    @SerialName("consumption_portion")
    val consumption_portion: Int? = null,
    @SerialName("consumption_total")
    val consumption_total: Int? = null,
    @SerialName("portion")
    val portion: Int? = null,
    @SerialName("total")
    val total: Int? = null,
    @SerialName("updated_at")
    val updated_at: String? = null,
)