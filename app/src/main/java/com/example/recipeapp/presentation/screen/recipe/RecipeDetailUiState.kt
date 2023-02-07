package com.example.recipeapp.presentation.screen.recipe

import com.example.recipeapp.domain.model.Recipe

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val throwable: Throwable? = null,
)
