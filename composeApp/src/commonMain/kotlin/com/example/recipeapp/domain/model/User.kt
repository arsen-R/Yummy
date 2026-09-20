package com.example.recipeapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val uid: String?,
    val email: String?,
    //val createdDate: Date = Date(),
    val provider: String
)
