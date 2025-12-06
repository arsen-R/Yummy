package com.example.recipeapp.presentation.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.component.BottomNavigationBar
import com.example.recipeapp.presentation.navigation.NavGraph
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavGraph(navHostController = navController)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    RecipeAppTheme {
        MainScreen()
    }
}