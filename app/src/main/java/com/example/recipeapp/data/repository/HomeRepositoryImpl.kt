package com.example.recipeapp.data.repository

import com.example.recipeapp.data.mapper.toRecipeList
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.RecipeList
import com.example.recipeapp.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi
): HomeRepository {
    override fun getListRecipe(): Flow<Resources<RecipeList>> {
        return flow {
            emit(Resources.Loading())
            val result = recipeApi.getListRecipes().body()
            emit(Resources.Success(result?.toRecipeList()!!))
        }.catch { exception ->
            emit(Resources.Error(exception = exception))
        }.flowOn(Dispatchers.IO)
    }
}