package com.example.recipeapp.presentation.navigation

import com.example.recipeapp.domain.util.Constants
import com.example.recipeapp.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


sealed class HomeNavScreen(
    val title: StringResource,
    val route: String,
    val icon: DrawableResource?
) {
    data object Search : HomeNavScreen(Res.string.search_screen, "search_screen", Res.drawable.round_search_24)
    data object Home : HomeNavScreen(Res.string.home_screen, "home_screen", Res.drawable.round_home_24)
    data object Favorite :
        HomeNavScreen(Res.string.favorite_screen, "favorite_screen", Res.drawable.round_favorite_24)

    data object Settings :
        HomeNavScreen(Res.string.settings_label, "settings_screen", Res.drawable.round_settings_24)

    data object RecipeDetail : HomeNavScreen(
        Res.string.favorite_screen,
        "recipe_detail_screen/{${Constants.DETAIL_ARGUMENT_KEY}}",
        null
    ) {
        fun passId(recipeId: Int) = "recipe_detail_screen/$recipeId"
    }
}

sealed class AuthNavScreen(val title: StringResource, val route: String) {
    data object Start : AuthNavScreen(Res.string.start_screen,"start_screen")
    data object SignUp : AuthNavScreen(Res.string.sign_up_label, "sign_up_screen")
    data object SignIn : AuthNavScreen(Res.string.sign_in_label, "sign_in_screen")
}

sealed class SettingNavScreen(val title: StringResource, val route: String) {
    data object AccountManagement :
        SettingNavScreen(Res.string.account_management_label, "account_management")
}