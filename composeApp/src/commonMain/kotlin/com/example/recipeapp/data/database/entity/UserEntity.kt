package com.example.recipeapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey
    val userId: String,
    val email: String?,
    //val createdDate: Date = Date(),
    val provider: String
)