package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRatingsDto(
    @SerializedName("count_negative")
    @Expose
    val count_negative: Int? = null,
    @SerializedName("count_positive")
    @Expose
    val count_positive: Int? = null,
    @SerializedName("score")
    @Expose
    val score: Double? = null,
)