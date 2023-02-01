package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.screen.favorite.FavoriteScreen
import com.example.recipeapp.presentation.screen.home.HomeScreen
import com.example.recipeapp.presentation.screen.search.SearchScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Search.route) {
            SearchScreen()
        }
        composable(route = Screen.Favorite.route) {
            FavoriteScreen()
        }
    }
}