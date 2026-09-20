package com.example.recipeapp.data.mapper

import com.example.recipeapp.domain.mapper.Mapper
import com.example.recipeapp.domain.model.User
import dev.gitlive.firebase.auth.FirebaseUser

class UserMapper : Mapper<FirebaseUser?, User?> {
    override fun fromDomain(value: FirebaseUser?): User {
        val linkedProvider = value?.providerData?.find { it.providerId != "firebase" }
        val providerId = linkedProvider?.providerId
            ?: value?.providerData?.firstOrNull()?.providerId
            ?: "unknown"
        return User(
            uid = value?.uid,
            email = value?.email,
            provider = providerId
        )
    }
}