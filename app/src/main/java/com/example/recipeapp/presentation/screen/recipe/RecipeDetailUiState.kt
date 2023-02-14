package com.example.recipeapp.presentation.screen.recipe

import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeList
import com.example.recipeapp.domain.model.RecipeResult

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val recipe: RecipeResult? = null,
    val similarRecipe: RecipeList? = null,
    val throwable: Throwable? = null,
)