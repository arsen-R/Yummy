package com.example.recipeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.recipeapp.data.database.entity.RecipeEntity
import com.example.recipeapp.data.database.entity.UserWithRecipes
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {

    @Transaction
    @Query("SELECT * FROM user_table WHERE userId = :userId")
    fun getAllRecipes(userId: String): Flow<UserWithRecipes>

    @Query("SELECT * FROM recipe_result_table")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM recipe_result_table WHERE id = :recipeId)")
    fun isRecipeSaved(recipeId: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity): Long

    @Query("DELETE FROM recipe_result_table WHERE id = :recipeId")
    suspend fun deleteRecipe(recipeId: Int)
}