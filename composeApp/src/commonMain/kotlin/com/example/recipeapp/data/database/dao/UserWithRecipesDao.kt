package com.example.recipeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.recipeapp.data.database.entity.UserRecipeCrossRef
import com.example.recipeapp.data.database.entity.UserWithRecipes
import kotlinx.coroutines.flow.Flow

@Dao
interface UserWithRecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserWithRecipes(crossRef: UserRecipeCrossRef): Long

    @Transaction
    @Query("SELECT * FROM user_table WHERE userId = :userId")
    fun getAllRecipes(userId: String): Flow<UserWithRecipes>
}