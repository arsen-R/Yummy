package com.example.recipeapp.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    ProfileScreen(modifier = modifier)
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
   Column(
       modifier = modifier.fillMaxSize()
   ) {
       Text(text = stringResource(id = R.string.profile_screen))
   }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    RecipeAppTheme {
        ProfileScreen()
    }
}