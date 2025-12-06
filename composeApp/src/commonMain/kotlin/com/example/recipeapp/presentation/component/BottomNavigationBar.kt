package com.example.recipeapp.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.navigation.HomeNavScreen
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val items = listOf(
        HomeNavScreen.Home,
        HomeNavScreen.Search,
        HomeNavScreen.Favorite,
        HomeNavScreen.Settings
    )
    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in items.map { it.route }
    AnimatedVisibility(
        visible = showBottomBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        modifier = modifier
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.Black,
            modifier = modifier
        ) {
            items.forEach { item ->
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                BottomNavigationItem(
                    selected = currentRoute == item.route, selectedContentColor = MaterialTheme.colors.onPrimary,
                    unselectedContentColor = MaterialTheme.colors.onPrimary.copy(0.4F),
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(painter = painterResource(item.icon!!), contentDescription = null)
                    },
                    label = {
                        Text(text = stringResource(item.title))
                    },
                    alwaysShowLabel = true
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    RecipeAppTheme {
        BottomNavigationBar(navController = navController)
    }
} 