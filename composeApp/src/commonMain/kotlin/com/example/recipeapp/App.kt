package com.example.recipeapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.navigation.RootNavGraph
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.app_name
import com.example.recipeapp.resources.theme_entries
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun App(modifier: Modifier = Modifier) {
    val viewModel: MainActivityViewModel = koinViewModel()
    val currentTheme = viewModel.selectAppThemeKey.collectAsStateWithLifecycle().value
    val isDarkTheme =
        when (stringArrayResource(Res.array.theme_entries)[currentTheme ?: 0]) {
            "light_theme_entry" -> false
            "dark_theme_entry" -> true
            else -> isSystemInDarkTheme()
        }
    RecipeAppTheme(darkTheme = isDarkTheme) {
        Scaffold(
            modifier = Modifier.Companion
                .fillMaxSize()
        ) { innerPadding ->
            Box(
                modifier = Modifier.Companion
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(innerPadding)
            ) {
                RootNavGraph(rememberNavController())
            }
        }
    }
}

@Composable
@Preview
fun AppPreview() {
    RecipeAppTheme {
        App()
    }
}