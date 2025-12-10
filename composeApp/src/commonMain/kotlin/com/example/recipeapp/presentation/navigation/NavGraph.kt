package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.recipeapp.domain.util.Constants
import com.example.recipeapp.presentation.screen.account_management.AccountManagementScreen
import com.example.recipeapp.presentation.screen.favorite.FavoriteScreen
import com.example.recipeapp.presentation.screen.home.HomeScreen
import com.example.recipeapp.presentation.screen.main.MainScreen
import com.example.recipeapp.presentation.screen.main.MainViewModel
import com.example.recipeapp.presentation.screen.profile.SettingsScreen
import com.example.recipeapp.presentation.screen.recipe_detail.RecipeDetailScreen
import com.example.recipeapp.presentation.screen.search.SearchScreen
import com.example.recipeapp.presentation.screen.signin.SignInScreen
import com.example.recipeapp.presentation.screen.signup.SignUpScreen
import com.example.recipeapp.presentation.screen.start.StartScreen
import org.koin.compose.viewmodel.koinViewModel

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
        ) {
            RecipeDetailScreen(navController = navHostController)
        }
        settingNavGraph(navHostController = navHostController)
    }
}

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    val viewModel: MainViewModel = koinViewModel()
    val isUserLoggedIn = viewModel.isUserLogIn.collectAsStateWithLifecycle().value
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        composable(route = Graph.HOME) {
            MainScreen()
        }
        authNavGraph(navHostController = navHostController)
    }
    LaunchedEffect(isUserLoggedIn) {
        val targetRoute = if (isUserLoggedIn != null) Graph.HOME else Graph.AUTH

        // avoid navigating to the same destination repeatedly
        val currentRoute = navHostController.currentBackStackEntry?.destination?.route
        if (currentRoute == targetRoute) return@LaunchedEffect

        navHostController.navigate(targetRoute) {
            // pop up to the graph start to clear previous stack entries
            popUpTo(navHostController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
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
