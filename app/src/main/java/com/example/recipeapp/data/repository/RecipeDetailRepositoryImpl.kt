package com.example.recipeapp.data.repository

import com.example.recipeapp.data.mapper.toRecipeList
import com.example.recipeapp.data.mapper.toRecipeResult
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.RecipeList
import com.example.recipeapp.domain.model.RecipeResult
import com.example.recipeapp.domain.repository.RecipeDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecipeDetailRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi
) : RecipeDetailRepository {
    override fun getRecipeDetail(recipeId: Int): Flow<Resources<RecipeResult>> {
        return flow {
            emit(Resources.Loading())
            delay(2000L)
            val result = recipeApi.getRecipeDetail(recipeId).body()
            emit(Resources.Success(result?.toRecipeResult()))
        }.catch { exception ->
            emit(Resources.Error(exception = exception))
        }.flowOn(Dispatchers.IO)
    }

    override fun getSimilarRecipes(recipeId: Int): Flow<Resources<RecipeList>> {
        return flow {
            emit(Resources.Loading())
            val result = recipeApi.getSimilarRecipes(recipeId = recipeId).body()
            emit(Resources.Success(result?.toRecipeList()))
        }.catch { exception ->
            emit(Resources.Error(exception = exception))
        }.flowOn(Dispatchers.IO)
    }
}