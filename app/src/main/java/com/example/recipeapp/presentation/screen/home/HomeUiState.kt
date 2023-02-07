package com.example.recipeapp.presentation.screen.home

import com.example.recipeapp.domain.model.RecipeResult

data class HomeUiState(
    val isLoading: Boolean = false,
    val recipe: List<RecipeResult?>? = null,
    val error: Throwable? = null
)
