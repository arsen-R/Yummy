package com.example.recipeapp.data.mapper

import cocoapods.FirebaseAuth.FIRUser
import com.example.recipeapp.domain.mapper.Mapper
import com.example.recipeapp.domain.model.User
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
class UserMapper: Mapper<FIRUser, User> {
    @OptIn(ExperimentalForeignApi::class)
    override fun fromDomain(value: FIRUser): User {
        return User(
            uid = value.uid(),
            email = value.email(),
            provider = value.providerID()
        )
    }
}