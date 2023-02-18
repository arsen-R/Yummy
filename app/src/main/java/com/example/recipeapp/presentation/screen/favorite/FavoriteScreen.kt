package com.example.recipeapp.presentation.screen.favorite

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipeapp.presentation.component.EmptyScreen
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.RecipeItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = hiltViewModel(), navController: NavController) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    if (uiState.isNullOrEmpty()) {
        EmptyScreen()
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(uiState) { recipe ->
                val isRecipeSaved = remember {
                    mutableStateOf(viewModel.isRecipeAdded(recipe.id!!))
                }
                RecipeItem(
                    recipeResult = recipe,
                    navController = navController,
                    isRecipeSaved = isRecipeSaved.value,
                    onSavedRecipeClick = {
                        isRecipeSaved != isRecipeSaved
                        if (isRecipeSaved.value) {
                            viewModel.removeRecipe(recipe.id!!)
                            Log.d("Added And Remove Recipe", "Removed:${isRecipeSaved.value}")
                        } else {
                            viewModel.insertRecipe(recipe)
                            Log.d("Added And Remove Recipe", "Added: ${isRecipeSaved.value}")
                        }
                    }
                )
            }
        }
    }
}