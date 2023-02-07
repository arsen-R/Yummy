package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R

@Composable
fun TopBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    toolbarState: Boolean
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            if (toolbarState) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h6,
                    color = Color.Black
                )
            }
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        navigationIcon = if (!toolbarState) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }
    )
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(navController = rememberNavController(), toolbarState = true)
}