package com.example.recipeapp.domain.model

data class User(
    val uid: String,
    val email: String?,
    //val createdDate: Date = Date(),
    val provider: String
)
