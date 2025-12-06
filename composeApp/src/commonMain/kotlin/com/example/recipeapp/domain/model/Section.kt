package com.example.recipeapp.domain.model

data class Section(
    val components: List<Component>? = null,
    val name: String? = null,
    val position: Int? = null,
)