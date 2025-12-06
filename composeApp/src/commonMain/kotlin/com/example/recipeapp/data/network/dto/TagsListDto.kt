package com.example.recipeapp.data.network.dto

import com.example.recipeapp.domain.model.Tag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagsListDto(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("results")
    val results: List<TagDto>? = null
)
