package com.example.recipeapp.data.paging

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import com.example.recipeapp.data.mapper.RecipeResultMapper
import com.example.recipeapp.data.network.RecipeDataSource
import com.example.recipeapp.domain.model.Recipe
import io.github.aakira.napier.Napier
import kotlinx.io.IOException

class RecipesPagingSource(
    private val api: RecipeDataSource,
    private val mapper: RecipeResultMapper

) : PagingSource<Int, Recipe>() {
    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(40) ?: anchorPage?.nextKey?.minus(40)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            val pageNumber = params.key ?: 0
            Napier.d(tag = RecipesPagingSource::class.simpleName) { "$pageNumber" }
            val response = api.getListRecipes(from = pageNumber)
            Napier.d(tag = RecipesPagingSource::class.simpleName) { "$response" }
            val recipes = mapper.toDomain(response).results?.filterNotNull() ?: emptyList()
            val prevKey = if (pageNumber == 0) null else pageNumber - 40
            val nextKey = if (recipes.isEmpty()) null else pageNumber + 40
            LoadResult.Page(
                data = recipes,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            Napier.e(tag = RecipesPagingSource::class.simpleName) { "IOException: $e" }
            LoadResult.Error(e)

        } catch (e: Exception) {
            Napier.e(tag = RecipesPagingSource::class.simpleName) { "Exception: $e" }
            LoadResult.Error(e)
        }
    }
}

