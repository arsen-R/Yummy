package com.example.recipeapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.recipeapp.R
import com.example.recipeapp.domain.util.Constants

sealed class Screen(
    @StringRes val title: Int ,
    val route: String,
    @DrawableRes val icon: Int?
) {
    object Home : Screen(R.string.home_screen, "home_screen", R.drawable.round_home_24)
    object Search: Screen(R.string.search_screen, "search_screen", R.drawable.round_search_24)
    object Favorite: Screen(R.string.favorite_screen,"favorite_screen", R.drawable.round_favorite_24)
    object Profile: Screen(R.string.profile_screen, "profile_screen", R.drawable.round_account_circle_24)
    object RecipeDetail: Screen(R.string.favorite_screen, "recipe_detail_screen/{${Constants.DETAIL_ARGUMENT_KEY}}", null) {
        fun passId(recipeId: Int) = "recipe_detail_screen/$recipeId"
    }
}