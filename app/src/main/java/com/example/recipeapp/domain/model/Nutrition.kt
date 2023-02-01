package com.example.recipeapp.domain.model

data class Nutrition(
    val calories: Int? = null,
    val carbohydrates: Int? = null,
    val fat: Int? = null,
    val fiber: Int? = null,
    val protein: Int? = null,
    val sugar: Int? = null,
    val updated_at: String? = null,
)