package com.example.recipeapp.presentation.screen.main

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.component.BottomNavigationBar
import com.example.recipeapp.presentation.component.TopBar
import com.example.recipeapp.presentation.navigation.NavGraph
import com.example.recipeapp.presentation.navigation.Screen
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val toolbarState = rememberSaveable {
        mutableStateOf(true)
    }
    val bottomBarState = rememberSaveable {
        mutableStateOf(true)
    }
    val currentRoute = navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(
        initialValue = navController.currentBackStackEntry
    )
    when (currentRoute.value?.destination?.route) {
        Screen.Home.route -> {
            bottomBarState.value = true
            toolbarState.value = true
        }
        Screen.Favorite.route -> {
            bottomBarState.value = true
            toolbarState.value = true
        }
        Screen.Search.route -> {
            bottomBarState.value = true
            toolbarState.value = true
        }
        else -> {
            bottomBarState.value = false
            toolbarState.value = false
        }
    }
    Scaffold(
        topBar = {
            TopBar(navController = navController, toolbarState = toolbarState.value)
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavGraph(navHostController = navController)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, bottomBarState = bottomBarState)
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