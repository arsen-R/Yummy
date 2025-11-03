package com.example.recipeapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.recipeapp.R
import com.example.recipeapp.domain.util.Constants

sealed class HomeNavScreen(
    @StringRes val title: Int,
    val route: String,
    @DrawableRes val icon: Int?
) {
    data object Home : HomeNavScreen(R.string.home_screen, "home_screen", R.drawable.round_home_24)
    data object Search : HomeNavScreen(R.string.search_screen, "search_screen", R.drawable.round_search_24)
    data object Favorite :
        HomeNavScreen(R.string.favorite_screen, "favorite_screen", R.drawable.round_favorite_24)

    data object Settings :
        HomeNavScreen(R.string.settings_label, "settings_screen", R.drawable.round_settings_24)

    data object RecipeDetail : HomeNavScreen(
        R.string.favorite_screen,
        "recipe_detail_screen/{${Constants.DETAIL_ARGUMENT_KEY}}",
        null
    ) {
        fun passId(recipeId: Int) = "recipe_detail_screen/$recipeId"
    }
}

sealed class AuthNavScreen(@StringRes val title: Int, val route: String) {
    data object Start : AuthNavScreen(R.string.start_screen,"start_screen")
    data object SignUp : AuthNavScreen(R.string.sign_up_label, "sign_up_screen")
    data object SignIn : AuthNavScreen(R.string.sign_in_label, "sign_in_screen")
}

sealed class SettingNavScreen(@StringRes val title: Int, val route: String) {
    data object AccountManagement :
        SettingNavScreen(R.string.account_management_label, "account_management")
}