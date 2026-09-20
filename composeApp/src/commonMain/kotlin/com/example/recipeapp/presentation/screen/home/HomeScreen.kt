package com.example.recipeapp.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.cash.paging.LoadState
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.PagingData
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.component.ErrorMessage
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.RecipeItem
import com.example.recipeapp.presentation.component.TopBar
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.home_screen
import com.example.recipeapp.resources.no_connection
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    navController: NavController,
) {
    val uiState = homeViewModel.uiState.collectAsLazyPagingItems()
    val isRecipeSaved = homeViewModel.isRecipeSaved.collectAsStateWithLifecycle().value
    Scaffold(
        topBar = {
            TopBar(title = stringResource(Res.string.home_screen))
        }, content = {
            Box(modifier = modifier.padding(it)) {
                HomeScreenContent(
                    modifier = modifier,
                    uiState = uiState,
                    navController = navController,
                    onFetchRecipeList = homeViewModel::fetchRecipeList,
                    onToggleSave = homeViewModel::toggleSave,
                    isRecipeSaved = isRecipeSaved
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    uiState: LazyPagingItems<Recipe>,
    navController: NavController,
    onFetchRecipeList: () -> Unit,
    onToggleSave: (Recipe) -> Unit,
    isRecipeSaved: Boolean
) {
    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = uiState.loadState.refresh is LoadStateLoading,
            onRefresh = { onFetchRecipeList() }
        )
    uiState.apply {
        when (loadState.refresh) {
            is LoadStateLoading -> {
                LoadingProgressBar()
                Napier.d(tag = "ScreenNavStateLog", message = "${uiState.loadState}")
            }

            is LoadStateError -> {
                ErrorMessage(message = stringResource(Res.string.no_connection), onRetryClick = {
                    onFetchRecipeList()
                })
                Napier.d(tag = "ScreenNavStateLog", message = "Error Refresh:")
            }

            is LoadStateNotLoading -> {
                Napier.d(tag = "ScreenNavStateLog", message = "NotLoading Refresh")
                Box(modifier = modifier.pullRefresh(pullRefreshState)) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = modifier
                    ) {
                        items(uiState.itemCount) { index ->
                            val recipes = uiState[index]
                            val isRecipeSavedState = remember {
                                mutableStateOf(isRecipeSaved)
                            }
                            RecipeItem(
                                recipeResult = recipes,
                                navController = navController,
                                isRecipeSaved = isRecipeSavedState.value,
                                onSavedRecipeClick = {
                                    isRecipeSavedState.value = !isRecipeSavedState.value
                                    onToggleSave(recipes!!)
                                }
                            )
                        }
                    }
                    PullRefreshIndicator(
                        state = pullRefreshState,
                        refreshing = uiState.loadState.refresh is LoadStateLoading,
                        modifier = modifier.align(
                            Alignment.TopCenter
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val recipes = listOf(
        Recipe(
            id = 1,
            name = "Recipe 1"
        ),
        Recipe(
            id = 2,
            name = "Recipe 2"
        ),
    )
    val pagingDataFlow = flowOf(PagingData.from(recipes))
    val lazyPagingItems = pagingDataFlow.collectAsLazyPagingItems()

    HomeScreenContent(
        uiState = lazyPagingItems,
        navController = navController,
        onFetchRecipeList = {},
        onToggleSave = {},
        isRecipeSaved = false
    )
}
