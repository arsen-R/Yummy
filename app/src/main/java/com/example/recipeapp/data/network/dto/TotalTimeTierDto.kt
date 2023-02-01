package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TotalTimeTierDto(
    @SerializedName("display_tier")
    @Expose
    val display_tier: String? = null,
    @SerializedName("tier")
    @Expose
    val tier: String? = null,
)