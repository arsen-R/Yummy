package com.example.recipeapp.presentation.screen.search

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.*
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Scaffold(topBar = {
        SearchAppBar(
            query = viewModel.searchText.value,
            onTextChanged = viewModel::getSearchText,
            onSearchRecipe = viewModel::onSearch
        )
    }, content = {
        Box(modifier = Modifier.padding(it)) {
            when {
                uiState.isLoading -> {
                    LoadingProgressBar()
                }
                uiState.result != null -> {
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(uiState.result) { recipe ->
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
                uiState.throwable != null -> {
                    ErrorMessage(
                        message = stringResource(id = R.string.no_connection),
                        onRetryClick = {
                            viewModel.onSearch(viewModel.searchText.value)
                        })
                }
            }
        }
        Log.d("Search Recipe in Net", "Home Screen = ${viewModel.searchText.value}")
        Log.d("List Recipe Response", "Search Screen = $uiState")
    })
}