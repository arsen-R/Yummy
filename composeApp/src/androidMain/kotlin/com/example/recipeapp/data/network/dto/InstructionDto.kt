package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InstructionDto(
    @SerializedName("appliance")
    @Expose
    val appliance: String? = null,
    @SerializedName("display_text")
    @Expose
    val display_text: String? = null,
    @SerializedName("end_time")
    @Expose
    val end_time: Int? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("position")
    @Expose
    val position: Int? = null,
    @SerializedName("start_time")
    @Expose
    val start_time: Int? = null,
    @SerializedName("temperature")
    @Expose
    val temperature: Int? = null,
)