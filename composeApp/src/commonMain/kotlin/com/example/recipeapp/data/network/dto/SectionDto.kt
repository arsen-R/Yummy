package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionDto(
    @SerialName("components")
    val components: List<ComponentDto?>? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("position")
    val position: Int? = null,
)