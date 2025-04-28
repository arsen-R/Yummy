package com.example.recipeapp.domain.util

import androidx.datastore.preferences.core.intPreferencesKey

object Constants {
    const val HEADER_API_KEY = "X-RapidAPI-Key"
    const val HEADER_API_HOST = "X-RapidAPI-Host"
    const val RECIPE_LIST_SIZE = 40
    const val DETAIL_ARGUMENT_KEY = "recipeId"

    const val DATABASE_NAME = "recipe.db"

    const val SIGN_IN_REQUEST = "SIGN_IN_REQUEST"
    const val SIGN_UP_REQUEST = "SIGN_UP_REQUEST"

    const val EMPTY_STRING = ""
    const val USER_PREFERENCES = "user_preferences"

    val APP_THEME_PREFERENCES = "APP_THEME_PREFERENCES"

    const val USERS_COLLECTION_PATH = "users"
}