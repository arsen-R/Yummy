package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.resources.*
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TopAppBarWithArrow(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = title ?: {},
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    painter = painterResource(Res.drawable.round_arrow_back_24),
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Preview
@Composable
fun DetailTopBarPreview() {
    RecipeAppTheme {
        TopAppBarWithArrow(navController = rememberNavController())
    }
}