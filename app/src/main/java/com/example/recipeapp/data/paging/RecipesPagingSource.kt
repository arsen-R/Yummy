package com.example.recipeapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recipeapp.data.mapper.toRecipe
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.domain.model.Recipe
import retrofit2.HttpException
import java.io.IOException

class RecipesPagingSource(
    private val api: RecipeApi
): PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        Log.d("RPS", "Anchor page ${state.anchorPosition}")
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(40) ?: anchorPage?.nextKey?.minus(40)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            val pageNumber = params.key ?: 0
            val response = api.getListRecipes(from = pageNumber)
            Log.d("RPS", "$pageNumber")
            if (response.isSuccessful) {
                val recipes = response.body()?.results?.map { it?.toRecipe()!! } ?: emptyList()
                val prevKey = if (pageNumber == 0) null else pageNumber - 40
                val nextKey = if (recipes.isEmpty()) null else pageNumber + 40
                LoadResult.Page(
                    data = recipes,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}