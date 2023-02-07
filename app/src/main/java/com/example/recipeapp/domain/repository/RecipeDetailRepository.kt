package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeDetailRepository {
    fun getRecipeDetail(recipeId: Int): Flow<Resources<Recipe>>
}