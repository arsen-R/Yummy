package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipeapp.domain.util.Constants
import com.example.recipeapp.presentation.screen.favorite.FavoriteScreen
import com.example.recipeapp.presentation.screen.home.HomeScreen
import com.example.recipeapp.presentation.screen.recipe.RecipeDetailScreen
import com.example.recipeapp.presentation.screen.recipe.RecipeDetailViewModel
import com.example.recipeapp.presentation.screen.search.SearchScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navHostController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen()
        }
        composable(route = Screen.Favorite.route) {
            FavoriteScreen()
        }
        composable(
            route = Screen.RecipeDetail.route,
            arguments = listOf(
                navArgument(Constants.DETAIL_ARGUMENT_KEY) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(Constants.DETAIL_ARGUMENT_KEY)?.let {
                val viewModel: @Composable (movieId: Int) -> RecipeDetailViewModel = { hiltViewModel() }
                RecipeDetailScreen(navController = navHostController, viewModel = viewModel(id))
            }
        }
    }
}