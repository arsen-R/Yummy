package com.example.recipeapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringArrayResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.navigation.RootNavGraph
import com.example.recipeapp.ui.theme.DarkCardBackground
import com.example.recipeapp.ui.theme.RecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.Companion.auto(
                    lightScrim = Color.TRANSPARENT,
                    darkScrim = DarkCardBackground.toArgb()
                ),
                navigationBarStyle = SystemBarStyle.Companion.auto(
                    lightScrim = Color.TRANSPARENT,
                    darkScrim = DarkCardBackground.toArgb()
                )
            )
            val viewModel: MainActivityViewModel = hiltViewModel()
            val currentTheme = viewModel.selectAppThemeKey.collectAsStateWithLifecycle().value
            val isDarkTheme =
                when (stringArrayResource(id = R.array.theme_entries)[currentTheme ?: 0]) {
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
    }
}