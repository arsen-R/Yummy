package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                modifier = modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
    )
}

@Preview(name = "Light mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode"
)
@Composable
fun TopBarPreview() {
    RecipeAppTheme {
        TopBar(
//            toolbarState = true,
            title = stringResource(id = R.string.app_name)
        )
    }
}