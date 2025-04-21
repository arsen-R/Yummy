package com.example.recipeapp.presentation.screen.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.component.BottomNavigationBar
import com.example.recipeapp.presentation.navigation.NavGraph
import com.example.recipeapp.presentation.navigation.HomeNavScreen
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
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

@Preview(showSystemUi = true, name = "Light mode")
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_2,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode"
)
@Composable
fun MainScreenPreview() {
    RecipeAppTheme {
        MainScreen()
    }
}