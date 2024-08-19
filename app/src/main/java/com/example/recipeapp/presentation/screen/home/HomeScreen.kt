package com.example.recipeapp.presentation.screen.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.component.ErrorMessage
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.RecipeItem
import com.example.recipeapp.presentation.component.TopBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {
    Log.d("ScreenNavStateLog", "Navigate to HomeScreen")
    val uiState = homeViewModel.uiState.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.home_screen))
        }, content = {
            Box(modifier = modifier.padding(it)) {
                HomeScreen(
                    modifier = modifier,
                    uiState = uiState,
                    navController = navController,
                    onFetchRecipeList = homeViewModel::fetchRecipeList,
                    onRecipeSaved = homeViewModel::isRecipeAdded,
                    onSaveRecipe = homeViewModel::insertRecipe,
                    onRemoveRecipe = homeViewModel::removeRecipe
                )
            }
        }
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: LazyPagingItems<Recipe>,
    navController: NavController,
    onFetchRecipeList: () -> Unit,
    onRecipeSaved: (recipeId: Int) -> Boolean,
    onSaveRecipe: (recipe: Recipe) -> Unit,
    onRemoveRecipe: (recipeId: Int) -> Unit
) {
    val swipeRefreshState =
        rememberSwipeRefreshState(isRefreshing = uiState.loadState.refresh is LoadState.Loading)
    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        onFetchRecipeList()
    }) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
        ) {
            items(uiState.itemCount) { index ->
                val recipes = uiState[index]
                val isRecipeSaved = remember {
                    mutableStateOf(onRecipeSaved(recipes?.id!!))
                }
                RecipeItem(
                    recipeResult = recipes,
                    navController = navController,
                    isRecipeSaved = isRecipeSaved.value,
                    onSavedRecipeClick = {
                        isRecipeSaved.value = !isRecipeSaved.value
                        if (isRecipeSaved.value) {
                            onSaveRecipe(recipes!!)
                        } else {
                            onRemoveRecipe(recipes?.id!!)
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
                    onFetchRecipeList()
                })
            }

            loadState.append is LoadState.Loading -> {
                //LoadingProgressBar()
            }

            loadState.append is LoadState.Error -> {
                ErrorMessage(message = stringResource(id = R.string.no_connection), onRetryClick = {
                    onFetchRecipeList()
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
