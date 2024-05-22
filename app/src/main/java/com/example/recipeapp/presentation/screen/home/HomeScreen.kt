package com.example.recipeapp.presentation.screen.home

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.ErrorMessage
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.RecipeItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val uiState = homeViewModel.uiState.collectAsLazyPagingItems()
    val swipeRefreshState =
        rememberSwipeRefreshState(isRefreshing = uiState.loadState.refresh is LoadState.Loading)
    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        homeViewModel.fetchRecipeList()
    }) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(uiState.itemCount) { index ->
                val recipes = uiState[index]
                val isRecipeSaved = remember {
                    mutableStateOf(homeViewModel.isRecipeAdded(recipeId = recipes?.id!!))
                }
                RecipeItem(
                    recipeResult = recipes,
                    navController = navController,
                    isRecipeSaved = isRecipeSaved.value,
                    onSavedRecipeClick = {
                        isRecipeSaved.value = !isRecipeSaved.value
                        if (isRecipeSaved.value) {
                            homeViewModel.insertRecipe(recipes!!)
                        } else {
                            homeViewModel.removeRecipe(recipes?.id!!)
                        }
                    }
                )
            }
        }
    }
    uiState.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                LoadingProgressBar()
            }
            loadState.refresh is LoadState.Error -> {
                ErrorMessage(message = stringResource(id = R.string.no_connection), onRetryClick = {
                    homeViewModel.fetchRecipeList()
                })
            }
            loadState.append is LoadState.Loading -> {
                //LoadingProgressBar()
            }
            loadState.append is LoadState.Error -> {
                ErrorMessage(message = stringResource(id = R.string.no_connection), onRetryClick = {
                    homeViewModel.fetchRecipeList()
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val navController = rememberNavController()
    HomeScreen(homeViewModel = homeViewModel, navController = navController)
}
