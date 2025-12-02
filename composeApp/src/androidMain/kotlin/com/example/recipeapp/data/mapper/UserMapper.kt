package com.example.recipeapp.data.mapper

import com.example.recipeapp.domain.mapper.Mapper
import com.example.recipeapp.domain.model.User
import com.google.firebase.auth.FirebaseUser

class UserMapper : Mapper<FirebaseUser, User> {
    override fun fromDomain(value: FirebaseUser): User {
        return User(
            uid = value.uid,
            email = value.email,
            provider = value.providerData[1].providerId
        )
    }
}