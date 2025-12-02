package com.example.recipeapp.presentation.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipeapp.resources.*
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SubtitleText(modifier: Modifier = Modifier, subtitleText: String) {
    Text(text = subtitleText, color = MaterialTheme.colors.onSecondary, modifier = modifier)
}

@Preview
@Composable
fun SubtitleTextPreview() {
    RecipeAppTheme {
        SubtitleText(subtitleText = stringResource(Res.string.sign_up_terms_of_service_label))
    }
}