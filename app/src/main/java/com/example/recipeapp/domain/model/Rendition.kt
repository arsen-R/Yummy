package com.example.recipeapp.domain.model

data class Rendition(
    val aspect: String? = null,
    val bit_rate: Int? = null,
    val container: String? = null,
    val content_type: String? = null,
    val duration: Int? = null,
    val file_size: Int? = null,
    val height: Int? = null,
    val maximum_bit_rate: Int? = null,
    val minimum_bit_rate: Int? = null,
    val name: String? = null,
    val poster_url: String? = null,
    val url: String? = null,
    val width: Int? = null,
)