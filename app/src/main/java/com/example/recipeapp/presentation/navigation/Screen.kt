package com.example.recipeapp.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.recipeapp.R

sealed class Screen(
    @StringRes val title: Int ,
    val route: String,
    val icon: Int?
) {
    object Home : Screen(R.string.home_screen, "home_screen", R.drawable.round_home_24)
    object Search: Screen(R.string.search_screen, "search_screen", R.drawable.round_search_24)
    object Favorite: Screen(R.string.favorite_screen,"favorite_screen", R.drawable.round_favorite_24)
}