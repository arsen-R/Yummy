package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NutritionDto(
    @SerializedName("calories")
    @Expose
    val calories: Int? = null,
    @SerializedName("carbohydrates")
    @Expose
    val carbohydrates: Int? = null,
    @SerializedName("fat")
    @Expose
    val fat: Int? = null,
    @SerializedName("fiber")
    @Expose
    val fiber: Int? = null,
    @SerializedName("protein")
    @Expose
    val protein: Int? = null,
    @SerializedName("sugar")
    @Expose
    val sugar: Int? = null,
    @SerializedName("updated_at")
    @Expose
    val updated_at: String? = null,
)