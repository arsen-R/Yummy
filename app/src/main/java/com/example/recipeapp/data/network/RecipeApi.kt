package com.example.recipeapp.data.network

import com.example.recipeapp.data.network.dto.RecipeDto
import com.example.recipeapp.data.network.dto.RecipeListDto
import com.example.recipeapp.data.network.dto.RecipeResultDto
import com.example.recipeapp.domain.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("/recipes/list")
    suspend fun getListRecipes(
        @Query("from") from: Int = 0,
        @Query("size") size: Int = Constants.RECIPE_LIST_SIZE
    ): Response<RecipeListDto>

    @GET("/recipes/get-more-info")
    suspend fun getRecipeDetail(
        @Query("id") recipeId: Int
    ): Response<RecipeResultDto>

    @GET("/recipes/list-similarities")
    suspend fun getSimilarRecipes(
        @Query("recipe_id") recipeId: Int
    ): Response<RecipeListDto>
}