package com.example.recipeapp.presentation.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.component.EmptyScreen
import com.example.recipeapp.presentation.component.RecipeItem
import com.example.recipeapp.presentation.component.TopBar

@Composable
internal fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavController
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Scaffold(topBar = {
        TopBar(title = stringResource(id = R.string.favorite_screen))
    }, content = {
        Box(modifier = modifier.padding(it)) {
            FavoriteScreen(
                modifier = modifier,
                navController = navController,
                uiState = uiState,
                onRecipeSaved = viewModel::isRecipeAdded,
                onSaveRecipe = viewModel::insertRecipe,
                onRemoveRecipe = viewModel::removeRecipe
            )
        }
    })
}

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    uiState: List<Recipe>,
    onRecipeSaved: (recipeId: Int) -> Boolean,
    onSaveRecipe: (recipe: Recipe) -> Unit,
    onRemoveRecipe: (recipeId: Int) -> Unit
) {
    if (uiState.isEmpty()) {
        EmptyScreen()
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(uiState) { recipe ->
                val isRecipeSaved = remember {
                    mutableStateOf(onRecipeSaved(recipe.id!!))
                }
                RecipeItem(
                    modifier = modifier,
                    recipeResult = recipe,
                    navController = navController,
                    isRecipeSaved = isRecipeSaved.value,
                    onSavedRecipeClick = {
                        isRecipeSaved != isRecipeSaved
                        if (isRecipeSaved.value) {
                            onRemoveRecipe(recipe.id!!)
                        } else {
                            onSaveRecipe(recipe)
                        }
                    }
                )
            }
        }
    }
}