package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.res.stringArrayResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.navigation.RootNavGraph
import com.example.recipeapp.presentation.screen.main.MainScreen
import com.example.recipeapp.ui.theme.RecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainActivityViewModel = hiltViewModel()
            val currentTheme = viewModel.selectAppThemeKey.collectAsStateWithLifecycle().value
            val isDarkTheme =
                when (stringArrayResource(id = R.array.theme_entries)[currentTheme ?: 0]) {
                    "light_theme_entry" -> false
                    "dark_theme_entry" -> true
                    else -> isSystemInDarkTheme()
                }
            RecipeAppTheme(darkTheme = isDarkTheme) {
                RootNavGraph(rememberNavController())
            }
        }
    }
}