package com.example.recipeapp.data.repository

import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.mapper.toRecipeList
import com.example.recipeapp.data.mapper.toRecipeResult
import com.example.recipeapp.data.mapper.toRecipeResultEntity
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.RecipeList
import com.example.recipeapp.domain.model.RecipeResult
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun getListRecipe(): Flow<Resources<RecipeList>> {
        return flow {
            emit(Resources.Loading())
            delay(3000L)
            val result = recipeApi.getListRecipes().body()
            emit(Resources.Success(result?.toRecipeList()!!))
        }.catch { exception ->
            emit(Resources.Error(exception = exception))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getRecipeDetail(recipeId: Int): Flow<Resources<RecipeResult>> {
        return flow {
            emit(Resources.Loading())
            delay(2000L)
            val result = recipeApi.getRecipeDetail(recipeId).body()
            emit(Resources.Success(result?.toRecipeResult()))
        }.catch { exception ->
            emit(Resources.Error(exception = exception))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSimilarRecipes(recipeId: Int): Flow<Resources<RecipeList>> {
        return flow {
            emit(Resources.Loading())
            val result = recipeApi.getSimilarRecipes(recipeId = recipeId).body()
            emit(Resources.Success(result?.toRecipeList()))
        }.catch { exception ->
            emit(Resources.Error(exception = exception))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun searchRecipe(query: String): Flow<Resources<RecipeList>> {
        return flow {
            emit(Resources.Loading())
            val result = recipeApi.getSearchRecipe(query).body()
            emit(Resources.Success(result?.toRecipeList()))
        }.catch {exception ->
            emit(Resources.Error(exception))
        }.flowOn(Dispatchers.IO)
    }

    override fun getAllSavedRecipes(): Flow<List<RecipeResult>> {
        return recipeDao.getAllRecipes().map { entities -> entities.map { it.toRecipeResult() } }
    }

    override suspend fun insertRecipe(recipeResult: RecipeResult) {
        recipeDao.insertRecipe(recipeResult.toRecipeResultEntity())
    }

    override suspend fun removeRecipe(recipeId: Int) {
        recipeDao.deleteRecipe(recipeId)
    }

    override fun isRecipeSaved(recipeId: Int): Boolean {
       return recipeDao.isRecipeSaved(recipeId)
    }
}