package com.example.recipeapp.domain.model

data class UserRatings(
    val count_negative: Int? = null,
    val count_positive: Int? = null,
    val score: Double? = null,
)