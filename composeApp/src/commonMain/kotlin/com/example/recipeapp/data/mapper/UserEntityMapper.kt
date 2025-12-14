package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.database.entity.UserEntity
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.User

class UserEntityMapper: BidirectionalMapper<User?, UserEntity?> {
    override fun toDomain(value: UserEntity?): User {
        return User(
            uid = value?.userId,
            email = value?.email,
            provider = value?.provider ?: "unknown"
        )
    }

    override fun fromDomain(value: User?): UserEntity {
        return UserEntity(
            userId = value?.uid ?: "",
            email = value?.email,
            provider = value?.provider ?: "unknown"
        )
    }
}