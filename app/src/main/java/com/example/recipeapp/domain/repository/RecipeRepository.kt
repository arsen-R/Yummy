package com.example.recipeapp.domain.repository

import androidx.paging.PagingData
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeList
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipeDetail(recipeId: Int): Flow<Resources<Recipe>>

    suspend fun getSimilarRecipes(recipeId: Int): Flow<Resources<RecipeList>>

    suspend fun searchRecipe(query: String): Flow<Resources<RecipeList>>
    fun getAllSavedRecipes(): Flow<List<Recipe>>

    suspend fun insertRecipe(recipeResult: Recipe)

    suspend fun removeRecipe(recipeId: Int)

    fun isRecipeSaved(recipeId: Int): Boolean

    fun getListRecipes(): Flow<PagingData<Recipe>>

    fun searchRecipes(query: String): Flow<PagingData<Recipe>>
}