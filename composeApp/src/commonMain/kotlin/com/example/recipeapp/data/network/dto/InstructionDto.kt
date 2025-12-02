package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstructionDto(
    @SerialName("appliance")
    val appliance: String? = null,
    @SerialName("display_text")
    val display_text: String? = null,
    @SerialName("end_time")
    val end_time: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("position")
    val position: Int? = null,
    @SerialName("start_time")
    val start_time: Int? = null,
    @SerialName("temperature")
    val temperature: Int? = null,
)