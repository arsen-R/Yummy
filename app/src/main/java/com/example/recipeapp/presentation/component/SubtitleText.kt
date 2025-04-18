package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.DoveGray
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun SubtitleText(modifier: Modifier = Modifier, subtitleText: String) {
    Text(text = subtitleText, color = MaterialTheme.colors.onSecondary, modifier = modifier)
}

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true

)
@Composable
fun SubtitleTextPreview() {
    RecipeAppTheme {
        SubtitleText(subtitleText = stringResource(id = R.string.sign_up_terms_of_service_label))
    }
}