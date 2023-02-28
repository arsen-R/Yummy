package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.RecipeList
import com.example.recipeapp.domain.model.RecipeResult
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getListRecipe(): Flow<Resources<RecipeList>>

    suspend fun getRecipeDetail(recipeId: Int): Flow<Resources<RecipeResult>>

    suspend fun getSimilarRecipes(recipeId: Int): Flow<Resources<RecipeList>>

    suspend fun searchRecipe(query: String): Flow<Resources<RecipeList>>
    fun getAllSavedRecipes(): Flow<List<RecipeResult>>

    suspend fun insertRecipe(recipeResult: RecipeResult)

    suspend fun removeRecipe(recipeId: Int)

    fun isRecipeSaved(recipeId: Int): Boolean

}