package com.example.recipeapp.presentation.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import com.example.recipeapp.presentation.component.ErrorMessage
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.RecipeItem
import com.example.recipeapp.presentation.component.SearchAppBar
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.no_connection
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
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
            uiState.apply {
                when {
                    loadState.append is LoadStateLoading -> {
                        LoadingProgressBar()
                    }

                    loadState.refresh is LoadStateError -> {
                        ErrorMessage(
                            message = stringResource(Res.string.no_connection),
                            onRetryClick = {
                                viewModel.onSearch(viewModel.searchText.value)
                            })
                    }
                    loadState.refresh is LoadStateNotLoading -> {
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
                    }
                }
            }
        }
        Napier.d(tag = "SearchScreenLogger") { "Home Screen = ${viewModel.searchText.value}" }
        Napier.d(tag = "SearchScreenLogger") { "Search Screen = $uiState" }
    })
}