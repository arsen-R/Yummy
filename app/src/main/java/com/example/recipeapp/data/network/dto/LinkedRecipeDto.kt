package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LinkedRecipeDto(
    @SerializedName("approved_at")
    @Expose
    val approved_at: Int? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("slug")
    @Expose
    val slug: String? = null,
    @SerializedName("thumbnail")
    @Expose
    val thumbnail_url: String? = null,
)