package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComponentDto(
    @SerialName("extra_comment")
    val extra_comment: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("ingredient")
    val ingredient: IngredientDto? = null,
    @SerialName("linked_recipe")
    val linked_recipe: LinkedRecipeDto? = null,
    @SerialName("measurements")
    val measurements: List<MeasurementDto>? = null,
    @SerialName("position")
    val position: Int? = null,
    @SerialName("raw_text")
    val raw_text: String? = null,
)