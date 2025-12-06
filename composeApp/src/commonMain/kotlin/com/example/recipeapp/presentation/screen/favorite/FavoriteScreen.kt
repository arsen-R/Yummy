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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.component.EmptyScreen
import com.example.recipeapp.presentation.component.RecipeItem
import com.example.recipeapp.presentation.component.TopBar
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.favorite_screen
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = koinViewModel(),
    navController: NavController
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val isRecipeSave = viewModel.isRecipeSaved.collectAsStateWithLifecycle().value
    Scaffold(topBar = {
        TopBar(title = stringResource(Res.string.favorite_screen))
    }, content = {
        Box(modifier = modifier.padding(it)) {
            FavoriteScreenContent(
                modifier = modifier,
                navController = navController,
                uiState = uiState,
                onToggleSave = viewModel::toggleSave,
//                onRecipeSaved = viewModel::isRecipeAdded,
//                onSaveRecipe = viewModel::insertRecipe,
//                onRemoveRecipe = viewModel::removeRecipe,
//                isRecipeSave = isRecipeSave
            )
        }
    })
}

@Composable
fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    uiState: FavoriteUiState,
    onToggleSave: (Recipe) -> Unit
) {
    if (uiState.recipes.isEmpty()) {
        EmptyScreen()
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(uiState.recipes) { recipe ->
                val isRecipeSaved = remember { mutableStateOf(uiState.savedIds.contains(recipe.id)) }
                Napier.d(tag = "FavoriteScreenLogger") { "isRecipeSaved: ${isRecipeSaved.value}" }
                RecipeItem(
                    modifier = modifier,
                    recipeResult = recipe,
                    navController = navController,
                    isRecipeSaved = isRecipeSaved.value,
                    onSavedRecipeClick = {
                        Napier.d(tag = "FavoriteScreenLogger") { "Clicked isRecipeSaved: ${isRecipeSaved.value}" }
                        isRecipeSaved != isRecipeSaved
//                        if (isRecipeSaved.value) {
//                            onRemoveRecipe(recipe.id!!)
//                        } else {
//                            onSaveRecipe(recipe)
//                        }
                        onToggleSave(recipe)
                    }
                )
            }
        }
    }
}