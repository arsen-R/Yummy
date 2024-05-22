package com.example.recipeapp.presentation.screen.recipe

import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeList

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val similarRecipe: RecipeList? = null,
    val throwable: Throwable? = null,
)