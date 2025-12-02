package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.recipeapp.resources.*
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

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

@Preview
@Composable
fun TopBarPreview() {
    RecipeAppTheme {
        TopBar(
//            toolbarState = true,
            title = stringResource(Res.string.app_name)
        )
    }
}