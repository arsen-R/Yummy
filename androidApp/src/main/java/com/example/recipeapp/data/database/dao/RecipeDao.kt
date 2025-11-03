package com.example.recipeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.data.database.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe_result_table")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM recipe_result_table WHERE id = :recipeId)")
    fun isRecipeSaved(recipeId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity): Long

    @Query("DELETE FROM recipe_result_table WHERE id = :recipeId")
    suspend fun deleteRecipe(recipeId: Int)
}