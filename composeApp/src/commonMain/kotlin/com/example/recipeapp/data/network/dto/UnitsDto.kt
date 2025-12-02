package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnitsDto(
    @SerialName("abbreviation")
    val abbreviation: String? = null,
    @SerialName("display_plural")
    val display_plural: String? = null,
    @SerialName("display_singular")
    val display_singular: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("system")
    val system: String? = null,
)