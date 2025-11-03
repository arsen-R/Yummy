package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MeasurementDto(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("quantity")
    @Expose
    val quantity: String? = null,
    @SerializedName("unit")
    @Expose
    val unit: UnitDto? = null,
)