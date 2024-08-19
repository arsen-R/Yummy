package com.example.recipeapp.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = DarkColor,
    primaryVariant = Purple700,
    surface = DarkCardBackground,
    onPrimary = Color.White,
    onSecondary = Color(0xFF2C98F0)
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Purple700,
    surface = Color.White,
    onPrimary = Color.Black,
    onSecondary = Color(0xFF2C98F0)
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun RecipeAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUIController = rememberSystemUiController()
    val colors = if (darkTheme) {
        systemUIController.setSystemBarsColor(Color.Black)
        systemUIController.setNavigationBarColor(DarkColor)
        DarkColorPalette
    } else {
        systemUIController.setSystemBarsColor(Color.White)
        systemUIController.setNavigationBarColor(Color.White)
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}