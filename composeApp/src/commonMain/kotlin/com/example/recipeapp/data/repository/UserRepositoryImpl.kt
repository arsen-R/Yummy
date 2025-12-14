package com.example.recipeapp.data.repository

import com.example.recipeapp.data.database.dao.UserDao
import com.example.recipeapp.data.database.entity.UserEntity
import com.example.recipeapp.data.mapper.UserEntityMapper
import com.example.recipeapp.domain.model.User
import com.example.recipeapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val userMapper: UserEntityMapper
) : UserRepository{
    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun getUserById(userId: String): Flow<User>? {
        return userDao.getUserById(userId)?.map { entity -> userMapper.toDomain(entity)  }
    }
}