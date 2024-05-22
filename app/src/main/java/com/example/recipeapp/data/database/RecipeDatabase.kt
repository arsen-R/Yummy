package com.example.recipeapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.database.entity.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao() : RecipeDao
}