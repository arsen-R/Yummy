package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.recipeapp.presentation.screen.main.MainViewModel
import com.example.recipeapp.presentation.screen.profile.SettingsScreen
import com.example.recipeapp.presentation.screen.recipe_detail.RecipeDetailScreen
import com.example.recipeapp.presentation.screen.recipe_detail.RecipeDetailViewModel
import com.example.recipeapp.presentation.screen.search.SearchScreen
import com.example.recipeapp.presentation.screen.account_management.AccountManagementScreen
import com.example.recipeapp.presentation.screen.signin.SignInScreen
import com.example.recipeapp.presentation.screen.signup.SignUpScreen
import com.example.recipeapp.presentation.screen.start.StartScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = HomeNavScreen.Home.route
    ) {
        composable(route = HomeNavScreen.Home.route) {
            HomeScreen(navController = navHostController)
        }
        composable(route = HomeNavScreen.Search.route) {
            SearchScreen(navController = navHostController)
        }
        composable(route = HomeNavScreen.Favorite.route) {
            FavoriteScreen(navController = navHostController)
        }
        composable(route = HomeNavScreen.Settings.route) {
            SettingsScreen(navController = navHostController)
        }
        composable(
            route = HomeNavScreen.RecipeDetail.route,
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
        settingNavGraph(navHostController = navHostController)
    }
}

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    val isUserLoggedIn = viewModel.isUserLogIn.collectAsStateWithLifecycle().value
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = if (!isUserLoggedIn) Graph.HOME else Graph.AUTH
    ) {
        composable(route = Graph.HOME) {
            MainScreen()
        }
        authNavGraph(navHostController = navHostController)

    }
}

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthNavScreen.Start.route
    ) {
        composable(route = AuthNavScreen.Start.route) {
            StartScreen(navController = navHostController)
        }
        composable(route = AuthNavScreen.SignUp.route) {
            SignUpScreen(navController = navHostController)
        }
        composable(route = AuthNavScreen.SignIn.route) {
            SignInScreen(navController = navHostController)
        }
    }
}

fun NavGraphBuilder.settingNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.SETTINGS,
        startDestination = SettingNavScreen.AccountManagement.route
    ) {
        composable(route = SettingNavScreen.AccountManagement.route) {
            AccountManagementScreen(navController = navHostController)
        }
    }
}

fun NavController.navigateToSignUp() {
    this.navigate(AuthNavScreen.SignUp.route)
}

fun NavController.navigateToSignIn() {
    this.navigate(AuthNavScreen.SignIn.route)
}

fun NavController.navigateToRecipeDetail(recipeId: Int) {
    this.navigate(route = HomeNavScreen.RecipeDetail.passId(recipeId))
}

fun NavController.navigateToAccountManagement() {
    this.navigate(route = SettingNavScreen.AccountManagement.route)
}

internal class RecipeArgs(val recipeId: Int) {
    constructor(saveStateHandle: SavedStateHandle) :
            this(checkNotNull(saveStateHandle[Constants.DETAIL_ARGUMENT_KEY]) as Int)
}
