package com.example.recipeapp.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.database.entity.RecipeEntity

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object RecipeDatabaseConstructor: RoomDatabaseConstructor<RecipeDatabase> {
    override fun initialize(): RecipeDatabase
}
@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
@ConstructedBy(RecipeDatabaseConstructor::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}