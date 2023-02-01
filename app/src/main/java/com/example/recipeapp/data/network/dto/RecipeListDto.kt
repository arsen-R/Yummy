package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeListDto(
    @SerializedName("count")
    @Expose
    val count: Int? = null,
    @SerializedName("results")
    @Expose
    val results: List<RecipeResultDto?>? = null,
)