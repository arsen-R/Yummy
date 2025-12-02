package com.example.recipeapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import com.example.recipeapp.ui.theme.DarkCardBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    lightScrim = Color.TRANSPARENT,
                    darkScrim = DarkCardBackground.toArgb()
                ),
                navigationBarStyle = SystemBarStyle.auto(
                    lightScrim = Color.TRANSPARENT,
                    darkScrim = DarkCardBackground.toArgb()
                )
            )
            App()
        }
    }
}