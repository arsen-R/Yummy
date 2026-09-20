package com.example.recipeapp.data.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "id"])
data class FavoriteRecipeEntityRef(
    val userId: String,
    val id: Int,
)
