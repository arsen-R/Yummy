package com.example.recipeapp.domain.model

data class RecipeList(
    val count: Int? = null,
    val results: List<RecipeResult?>? = null,
)