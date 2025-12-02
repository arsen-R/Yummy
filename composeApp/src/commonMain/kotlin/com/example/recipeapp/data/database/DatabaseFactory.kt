package com.example.recipeapp.data.database

expect class DatabaseFactory {

    fun createDatabase(): RecipeDatabase
}
