package com.example.recipeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.recipeapp.core.Result
import com.example.recipeapp.core.safeApiCall
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.mapper.RecipeEntityMapper
import com.example.recipeapp.data.mapper.RecipeMapper
import com.example.recipeapp.data.mapper.RecipeResultMapper
import com.example.recipeapp.data.network.RecipeDataSource
import com.example.recipeapp.data.paging.RecipesPagingSource
import com.example.recipeapp.data.paging.SearchRecipePagingSource
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeResult
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepositoryImpl(
    private val recipeApi: RecipeDataSource,
    private val recipeDao: RecipeDao,
    private val recipeMapper: RecipeMapper,
    private val recipeResultMapper: RecipeResultMapper,
    private val recipeEntityMapper: RecipeEntityMapper
) : RecipeRepository {
    override suspend fun getRecipeDetail(recipeId: Int): Flow<Result<Recipe>> {
        return safeApiCall {
            val result = recipeApi.getRecipeDetail(recipeId)
            recipeMapper.toDomain(result)
        }
    }

    override suspend fun getSimilarRecipes(recipeId: Int): Flow<Result<RecipeResult>> {
        return safeApiCall {
            val result = recipeApi.getSimilarRecipes(recipeId)
            recipeResultMapper.toDomain(result)
        }
    }

    override suspend fun searchRecipe(query: String): Flow<Result<RecipeResult>> {
        return safeApiCall {
            val result = recipeApi.getSearchRecipe(query)
            recipeResultMapper.toDomain(result)
        }
    }

    override suspend fun getAllSavedRecipes(): Flow<List<Recipe>> {
        return recipeDao.getAllRecipes().map { entities -> entities.map { recipeEntityMapper.toDomain(it) } }
    }

    override suspend fun insertRecipe(recipeResult: Recipe) {
        recipeDao.insertRecipe(recipeEntityMapper.fromDomain(recipeResult))
    }

    override suspend fun removeRecipe(recipeId: Int) {
        recipeDao.deleteRecipe(recipeId)
    }

    override suspend fun isRecipeSaved(recipeId: Int): Flow<Boolean> {
       return recipeDao.isRecipeSaved(recipeId)
    }

    override fun getListRecipes(): Flow<PagingData<Recipe>> {
        return Pager(
            PagingConfig(
                pageSize = 80,
                enablePlaceholders = true
            )
        ) {
            RecipesPagingSource(api = recipeApi, mapper = recipeResultMapper)
        }.flow
    }

    override fun searchRecipes(query: String): Flow<PagingData<Recipe>> {
        return Pager(
            PagingConfig(
                pageSize = 80,
                enablePlaceholders = true
            )
        ) {
            SearchRecipePagingSource(api = recipeApi, query = query, mapper = recipeResultMapper)
        }.flow
    }
}