package com.example.recipeapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recipeapp.data.mapper.toRecipe
import com.example.recipeapp.data.network.RecipeApi
import com.example.recipeapp.domain.model.Recipe
import retrofit2.HttpException

class SearchRecipePagingSource(
    private val api: RecipeApi,
    private val query: String
) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(40) ?: anchorPage?.nextKey?.minus(40)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            val paramPage = params.key ?: 0
            val response = api.getSearchRecipe(query = query, from = paramPage)
            if (response.isSuccessful) {
                val recipes = response.body()?.results?.map { it?.toRecipe()!! } ?: emptyList()
                val prevKey = if (paramPage == 0) null else paramPage - 40
                val nextKey = if (recipes.isEmpty()) null else paramPage + 40
                LoadResult.Page(
                    data = recipes,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}