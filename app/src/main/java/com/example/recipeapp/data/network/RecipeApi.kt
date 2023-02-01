package com.example.recipeapp.data.network

import com.example.recipeapp.data.network.dto.RecipeListDto
import com.example.recipeapp.domain.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("/recipes/list")
    suspend fun getListRecipes(
        @Query("size") size: Int = Constants.RECIPE_LIST_SIZE
    ): Response<RecipeListDto>
}