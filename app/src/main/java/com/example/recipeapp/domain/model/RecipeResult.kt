package com.example.recipeapp.domain.model

data class RecipeResult(
    val count: Int? = null,
    val results: List<Recipe?>?,
)