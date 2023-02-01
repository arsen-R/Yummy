package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.util.Resources
import com.example.recipeapp.domain.model.RecipeList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getListRecipe(): Flow<Resources<RecipeList>>
}