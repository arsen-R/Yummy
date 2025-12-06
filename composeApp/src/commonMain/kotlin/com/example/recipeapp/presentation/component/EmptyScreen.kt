package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.empty_saved_recipe
import com.example.recipeapp.resources.round_bookmark_border_24
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmptyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(Res.drawable.round_bookmark_border_24),
            contentDescription = null,
            modifier = modifier.size(50.dp),
            tint = MaterialTheme.colors.onPrimary
        )
        Text(
            text = "Saved Recipes",
            color = MaterialTheme.colors.onPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(Res.string.empty_saved_recipe),
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun EmptyScreenPreview() {
    RecipeAppTheme {
        EmptyScreen()
    }
}