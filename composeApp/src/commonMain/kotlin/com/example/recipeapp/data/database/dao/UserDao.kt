package com.example.recipeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recipeapp.data.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE userId = :userId LIMIT 1")
    fun getUserById(userId: String): Flow<UserEntity>?

    @Query("SELECT EXISTS (SELECT 1 FROM user_table WHERE userId = :userId)")
    suspend fun isUserExist(userId: String): Boolean

    @Query("DELETE FROM user_table WHERE userId = :userId")
    suspend fun deleteUser(userId: String)
}