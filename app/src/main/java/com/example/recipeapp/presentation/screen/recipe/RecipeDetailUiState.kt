package com.example.recipeapp.presentation.screen.recipe

import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeResult

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val similarRecipe: RecipeResult? = null,
    val throwable: Throwable? = null,
)

sealed interface RecipeDetailState {
    object Idle: RecipeDetailState
    object Loading: RecipeDetailState
    object Success: RecipeDetailState
    object Error: RecipeDetailState
}