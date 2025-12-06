package com.example.recipeapp.data.network

import com.example.recipeapp.data.network.dto.RecipeDto
import com.example.recipeapp.data.network.dto.RecipeResultDto
import com.example.recipeapp.domain.util.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RecipeDataSource(
    private val client: HttpClient,
) {
    suspend fun getListRecipes(
        from: Int = 0,
        size: Int = Constants.RECIPE_LIST_SIZE
    ): RecipeResultDto {
        return client.get("https://tasty.p.rapidapi.com/recipes/list") {
            parameter("from", from)
            parameter("size", size)
        }.body()
    }

    suspend fun getRecipeDetail(
        recipeId: Int
    ): RecipeDto {
        return client.get("https://tasty.p.rapidapi.com/recipes/get-more-info") {
            parameter("id", recipeId)
        }.body()
    }

    suspend fun getSimilarRecipes(
        recipeId: Int,
    ): RecipeResultDto {
        return client.get("https://tasty.p.rapidapi.com/recipes/list-similarities") {
            parameter("id", recipeId)
        }.body()
    }

    suspend fun getSearchRecipe(
        query: String,
        from: Int = 0,
        size: Int = Constants.RECIPE_LIST_SIZE
    ): RecipeResultDto {
        return client.get("https://tasty.p.rapidapi.com/recipes/list") {
            parameter("q", query)
            parameter("from", from)
            parameter("size", size)
        }.body()
    }
}