package com.example.recipeapp.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UserWithRecipes(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "id",
        associateBy = Junction(FavoriteRecipeEntityRef::class)
    )
    val recipes: List<RecipeEntity>,
)