package com.example.recipeapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.example.recipeapp.data.database.dao.FavoriteRecipeDao
import com.example.recipeapp.data.database.dao.UserDao
import com.example.recipeapp.data.database.entity.FavoriteRecipeEntityRef
import com.example.recipeapp.data.database.entity.RecipeEntity
import com.example.recipeapp.data.database.entity.UserEntity

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object RecipeDatabaseConstructor: RoomDatabaseConstructor<RecipeDatabase> {
    override fun initialize(): RecipeDatabase
}
@Database(
    entities = [RecipeEntity::class, UserEntity::class, FavoriteRecipeEntityRef::class],
    version = 2,
    exportSchema = true
)
@ConstructedBy(RecipeDatabaseConstructor::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao() : FavoriteRecipeDao
    abstract fun userDao(): UserDao
}