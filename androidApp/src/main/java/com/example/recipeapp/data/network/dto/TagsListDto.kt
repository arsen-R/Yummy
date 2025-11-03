package com.example.recipeapp.data.network.dto

import com.example.recipeapp.domain.model.Tag
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TagsListDto(
    @SerializedName("count")
    @Expose
    val count: Int? = null,
    @SerializedName("results")
    @Expose
    val results: List<Tag>? = null
)
