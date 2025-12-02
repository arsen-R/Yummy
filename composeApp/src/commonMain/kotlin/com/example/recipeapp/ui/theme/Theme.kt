package com.example.recipeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkColor,
    primaryVariant = Purple700,
    surface = DarkCardBackground,
    onPrimary = Color.White,
    onSecondary = Color.LightGray,
    //background = Shark
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Purple700,
    surface = Color.White,
    onPrimary = Color.Black,
    onSecondary = DoveGray
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
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}