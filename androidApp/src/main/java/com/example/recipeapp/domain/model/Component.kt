package com.example.recipeapp.domain.model

data class Component(
    val extra_comment: String? = null,
    val id: Int? = null,
    val ingredient: Ingredient? = null,
    val linked_recipe: LinkedRecipe? = null,
    val measurements: List<Measurement>? = null,
    val position: Int? = null,
    val raw_text: String? = null,
)