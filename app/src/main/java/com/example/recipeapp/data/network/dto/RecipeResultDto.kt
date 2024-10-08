package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeResultDto(
    @SerializedName("count")
    @Expose
    val count: Int?,
    @SerializedName("results")
    @Expose
    val results: List<RecipeDto?>?,
)