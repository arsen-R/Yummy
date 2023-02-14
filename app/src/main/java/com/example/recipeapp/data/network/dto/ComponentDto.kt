package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ComponentDto(
    @SerializedName("extra_comment")
    @Expose
    val extra_comment: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("ingredient")
    @Expose
    val ingredient: IngredientDto? = null,
    @SerializedName("linked_recipe")
    @Expose
    val linked_recipe: LinkedRecipeDto? = null,
    @SerializedName("measurements")
    @Expose
    val measurements: List<MeasurementDto>? = null,
    @SerializedName("position")
    @Expose
    val position: Int? = null,
    @SerializedName("raw_text")
    @Expose
    val raw_text: String? = null,
)