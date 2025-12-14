package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.database.entity.UserEntity
import com.example.recipeapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: UserEntity)
    suspend fun getUserById(userId: String): Flow<User>?
}