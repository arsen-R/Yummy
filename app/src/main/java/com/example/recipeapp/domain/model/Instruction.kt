package com.example.recipeapp.domain.model

data class Instruction(
    val appliance: String? = null,
    val display_text: String? = null,
    val end_time: Int? = null,
    val id: Int? = null,
    val position: Int? = null,
    val start_time: Int? = null,
    val temperature: Int? = null,
)