package com.example.recipeapp.presentation.screen.favorite

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipeapp.presentation.component.EmptyScreen
import com.example.recipeapp.presentation.component.RecipeItem

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = hiltViewModel(), navController: NavController) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    if (uiState.isEmpty()) {
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
                        } else {
                            viewModel.insertRecipe(recipe)
                        }
                    }
                )
            }
        }
    }
}