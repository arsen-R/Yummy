package com.example.recipeapp.presentation.screen.verify

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun VerifyScreen(modifier: Modifier = Modifier) {

}

@Preview(
    name = "Light Mode",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun VerifyScreenBodyPreview() {
    RecipeAppTheme {

    }
}