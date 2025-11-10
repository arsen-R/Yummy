package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IngredientDto(
    @SerializedName("created_at")
    @Expose
    val created_at: Int? = null,
    @SerializedName("display_plural")
    @Expose
    val display_plural: String? = null,
    @SerializedName("display_singular")
    @Expose
    val display_singular: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("updated_at")
    @Expose
    val updated_at: Int? = null,
)