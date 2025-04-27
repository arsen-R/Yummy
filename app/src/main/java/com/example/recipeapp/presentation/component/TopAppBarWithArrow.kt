package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.RecipeAppTheme

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
                    imageVector = ImageVector.vectorResource(R.drawable.round_arrow_back_24),
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Preview(name = "Light mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode"
)
@Composable
fun DetailTopBarPreview() {
    RecipeAppTheme {
        TopAppBarWithArrow(navController = rememberNavController())
    }
}