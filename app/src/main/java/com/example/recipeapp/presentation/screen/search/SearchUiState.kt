package com.example.recipeapp.presentation.screen.search

import com.example.recipeapp.domain.model.RecipeResult
import com.example.recipeapp.domain.model.Tag

data class SearchUiState(
    val isLoading: Boolean = false,
    val result: List<RecipeResult?>? = null,
    val throwable: Throwable? = null,
)