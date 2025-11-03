package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PriceDto(
    @SerializedName("consumption_portion")
    @Expose
    val consumption_portion: Int? = null,
    @SerializedName("consumption_total")
    @Expose
    val consumption_total: Int? = null,
    @SerializedName("portion")
    @Expose
    val portion: Int? = null,
    @SerializedName("total")
    @Expose
    val total: Int? = null,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String? = null,
)