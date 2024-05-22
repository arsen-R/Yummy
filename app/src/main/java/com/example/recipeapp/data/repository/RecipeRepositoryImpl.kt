package com.example.recipeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.mapper.toRecipe
import com.example.recipeapp.data.mapper.toRecipeEntity
import com.example.recipeapp.data.mapper.toRecipeList
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.data.paging.RecipesPagingSource
import com.example.recipeapp.data.paging.SearchRecipePagingSource
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeList
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override suspend fun getRecipeDetail(recipeId: Int): Flow<Resources<Recipe>> {
        return flow {
            emit(Resources.Loading())
            delay(1500L)
            val result = recipeApi.getRecipeDetail(recipeId).body()
            emit(Resources.Success(result?.toRecipe()))
        }.catch { exception ->
            emit(Resources.Error(exception = exception))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getSimilarRecipes(recipeId: Int): Flow<Resources<RecipeList>> {
        return flow {
            emit(Resources.Loading())
            delay(1500L)
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

    override fun getAllSavedRecipes(): Flow<List<Recipe>> {
        return recipeDao.getAllRecipes().map { entities -> entities.map { it.toRecipe() } }
    }

    override suspend fun insertRecipe(recipeResult: Recipe) {
        recipeDao.insertRecipe(recipeResult.toRecipeEntity())
    }

    override suspend fun removeRecipe(recipeId: Int) {
        recipeDao.deleteRecipe(recipeId)
    }

    override fun isRecipeSaved(recipeId: Int): Boolean {
       return recipeDao.isRecipeSaved(recipeId)
    }

    override fun getListRecipes(): Flow<PagingData<Recipe>> {
        return Pager(
            PagingConfig(
                pageSize = 80,
                enablePlaceholders = true
            )
        ) {
            RecipesPagingSource(api = recipeApi)
        }.flow
    }

    override fun searchRecipes(query: String): Flow<PagingData<Recipe>> {
        return Pager(
            PagingConfig(
                pageSize = 80,
                enablePlaceholders = true
            )
        ) {
            SearchRecipePagingSource(api = recipeApi, query = query)
        }.flow
    }
}