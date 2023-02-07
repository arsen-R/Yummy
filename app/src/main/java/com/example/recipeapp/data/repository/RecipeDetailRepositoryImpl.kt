package com.example.recipeapp.data.repository

import com.example.recipeapp.data.mapper.toRecipe
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.repository.RecipeDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecipeDetailRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi
) : RecipeDetailRepository {
    override fun getRecipeDetail(recipeId: Int): Flow<Resources<Recipe>> {
        return flow {
            emit(Resources.Loading())
            val result = recipeApi.getRecipeDetail(recipeId).body()
            emit(Resources.Success(result?.toRecipe()))
        }.catch { exception ->
            emit(Resources.Error(exception = exception))
        }.flowOn(Dispatchers.IO)
    }
}