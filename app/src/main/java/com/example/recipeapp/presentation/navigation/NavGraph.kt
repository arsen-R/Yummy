package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.recipeapp.domain.util.Constants
import com.example.recipeapp.presentation.screen.favorite.FavoriteScreen
import com.example.recipeapp.presentation.screen.home.HomeScreen
import com.example.recipeapp.presentation.screen.main.MainScreen
import com.example.recipeapp.presentation.screen.profile.ProfileScreen
import com.example.recipeapp.presentation.screen.recipe.RecipeDetailScreen
import com.example.recipeapp.presentation.screen.recipe.RecipeDetailViewModel
import com.example.recipeapp.presentation.screen.search.SearchScreen
import com.example.recipeapp.presentation.screen.signin.SignInScreen
import com.example.recipeapp.presentation.screen.signup.SignUpScreen
import com.example.recipeapp.presentation.screen.start.StartScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navHostController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navHostController)
        }
        composable(route = Screen.Favorite.route) {
            FavoriteScreen(navController = navHostController)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navHostController)
        }
        composable(
            route = Screen.RecipeDetail.route,
            arguments = listOf(
                navArgument(Constants.DETAIL_ARGUMENT_KEY) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(Constants.DETAIL_ARGUMENT_KEY)?.let {
                val viewModel: @Composable (movieId: Int) -> RecipeDetailViewModel =
                    { hiltViewModel() }
                RecipeDetailScreen(navController = navHostController, viewModel = viewModel(id))
            }
        }
        composable(route = Screen.Start.route) {
            StartScreen(navController = navHostController)
        }
        composable(route = Screen.SignUp.route) {
            SignUpScreen(navController = navHostController)
        }
        composable(route = Screen.SignIn.route) {
            SignInScreen(navController = navHostController)
        }
    }
}

fun NavController.navigateToSignUp() {
    this.navigate(Screen.SignUp.route)
}

fun NavController.navigateToSignIn() {
    this.navigate(Screen.SignIn.route)
}

fun NavController.navigateToHome() {
    this.navigate(Screen.Home.route)
}

fun NavController.navigateToRecipeDetail(recipeId: Int) {
    this.navigate(route = Screen.RecipeDetail.passId(recipeId))
}

internal class RecipeArgs(val recipeId: Int) {
    constructor(saveStateHandle: SavedStateHandle) :
            this(checkNotNull(saveStateHandle[Constants.DETAIL_ARGUMENT_KEY]) as Int)
}
