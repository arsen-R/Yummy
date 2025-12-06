package com.example.recipeapp.domain.repository

import androidx.paging.PagingData
import com.example.recipeapp.core.Result
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeResult
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipeDetail(recipeId: Int): Flow<Result<Recipe>>

    suspend fun getSimilarRecipes(recipeId: Int): Flow<Result<RecipeResult>>

    suspend fun searchRecipe(query: String): Flow<Result<RecipeResult>>
    suspend fun getAllSavedRecipes(): Flow<List<Recipe>>

    suspend fun insertRecipe(recipeResult: Recipe)

    suspend fun removeRecipe(recipeId: Int)

    suspend fun isRecipeSaved(recipeId: Int): Flow<Boolean>

    fun getListRecipes(): Flow<PagingData<Recipe>>

    fun searchRecipes(query: String): Flow<PagingData<Recipe>>
}