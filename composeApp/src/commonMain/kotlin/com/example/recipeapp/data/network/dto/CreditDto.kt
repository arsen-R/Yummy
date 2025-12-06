package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditDto(
    @SerialName("name")
    val name: String? = null,
    @SerialName("type")
    val type: String? = null,
)