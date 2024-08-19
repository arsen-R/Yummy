package com.example.recipeapp.domain.model

import java.util.Date

data class User(
    val uid: String,
    val email: String?,
    val createdDate: Date = Date()
)
