package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreditDto(
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null,
)