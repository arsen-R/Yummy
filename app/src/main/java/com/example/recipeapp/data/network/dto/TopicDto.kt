package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TopicDto(
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("slug")
    @Expose
    val slug: String? = null,
)