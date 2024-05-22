package com.example.recipeapp.presentation.screen.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.ErrorMessage
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.RecipeItem
import com.example.recipeapp.presentation.component.SearchAppBar

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {
    val uiState = viewModel.uiState.collectAsLazyPagingItems()
    Scaffold(topBar = {
        SearchAppBar(
            query = viewModel.searchText.value,
            onTextChanged = viewModel::getSearchText,
            onSearchRecipe = viewModel::onSearch
        )
    }, content = {
        Box(modifier = Modifier.padding(it)) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(uiState.itemCount) { index ->
                    val recipe = uiState[index]
                    val isRecipeSaved = remember {
                        mutableStateOf(viewModel.isRecipeAdded(recipe?.id!!))
                    }
                    RecipeItem(
                        recipeResult = recipe,
                        navController = navController,
                        isRecipeSaved = isRecipeSaved.value,
                        onSavedRecipeClick = {
                            isRecipeSaved.value = !isRecipeSaved.value
                            if (isRecipeSaved.value) {
                                viewModel.insertRecipe(recipe!!)
                            } else {
                                viewModel.removeRecipe(recipe?.id!!)
                            }
                        }
                    )
                }
            }

            uiState.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        LoadingProgressBar()
                    }
                    loadState.refresh is LoadState.Error -> {
                        ErrorMessage(
                            message = stringResource(id = R.string.no_connection),
                            onRetryClick = {
                                viewModel.onSearch(viewModel.searchText.value)
                            })
                    }
                    loadState.append is LoadState.Loading -> {

                    }
                    loadState.append is LoadState.Error -> {
                        ErrorMessage(
                            message = stringResource(id = R.string.no_connection),
                            onRetryClick = {
                                viewModel.onSearch(viewModel.searchText.value)
                            })
                    }
                }
            }
        }
        Log.d("Search Recipe in Net", "Home Screen = ${viewModel.searchText.value}")
        Log.d("List Recipe Response", "Search Screen = $uiState")
    })
}