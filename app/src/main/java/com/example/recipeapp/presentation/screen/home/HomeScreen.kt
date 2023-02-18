package com.example.recipeapp.presentation.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.ErrorMessage
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.RecipeItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val context = LocalContext.current
    val uiState = homeViewModel.uiState.collectAsStateWithLifecycle().value

    val refreshState = homeViewModel.uiState.collectAsStateWithLifecycle().value.isLoading

    val swipeRefreshState =
        rememberSwipeRefreshState(isRefreshing = uiState.isLoading != refreshState)

    when {
        uiState.isLoading -> {
            LoadingProgressBar()
        }
        uiState.recipe != null -> {
            SwipeRefresh(state = swipeRefreshState, onRefresh = {
                homeViewModel.fetchRecipeList()
            }) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(uiState.recipe) { recipe ->
                        val isRecipeSaved = remember {
                            mutableStateOf(homeViewModel.isRecipeAdded(recipe?.id!!))
                        }
                        RecipeItem(
                            recipeResult = recipe,
                            navController = navController,
                            isRecipeSaved = isRecipeSaved.value,
                            onSavedRecipeClick = {
                                isRecipeSaved.value = !isRecipeSaved.value
                                if (isRecipeSaved.value) {
                                    homeViewModel.insertRecipe(recipe!!)
                                    Log.d("Added And Remove Recipe", "${isRecipeSaved.value}")
                                    Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show()
                                } else {homeViewModel.removeRecipe(recipe?.id!!)
                                    Log.d("Added And Remove Recipe", "${isRecipeSaved.value}")
                                    Toast.makeText(context, "Removed", Toast.LENGTH_LONG).show()
                                }
                            }
                        )
                    }
                }
            }
        }
        uiState.error != null -> {
            ErrorMessage(message = stringResource(id = R.string.no_connection), onRetryClick = {
                homeViewModel.fetchRecipeList()
            })
        }
    }
    Log.d("List Recipe Response", "Home Screen = $uiState")
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val navController = rememberNavController()
    HomeScreen(homeViewModel = homeViewModel, navController = navController)
}
