package com.example.recipeapp.presentation.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.BottomNavigationBar
import com.example.recipeapp.presentation.navigation.NavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavGraph(navHostController = navController)
            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    )
}

@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_2,
)
@Composable
fun MainScreenPreview() {
    MainScreen()
}