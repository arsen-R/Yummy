package com.example.recipeapp.presentation.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recipeapp.presentation.component.ErrorMessage
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.RecipeItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    
    val uiState = homeViewModel.uiState.collectAsStateWithLifecycle().value

    val refreshState = homeViewModel.uiState.collectAsStateWithLifecycle().value.isLoading

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = uiState.isLoading != refreshState)

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
                        RecipeItem(
                            recipeResult = recipe,
                            onItemRecipeClick = {
                                Toast.makeText(context, recipe?.name, Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }
            }
        }
        uiState.error != null -> {
            ErrorMessage(message = "Hmm... it looks like you're offline.", onRetryClick = {
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
    HomeScreen(homeViewModel = homeViewModel)
}
