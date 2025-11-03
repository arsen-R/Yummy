package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BrandDto(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("image_url")
    @Expose
    val image_url: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("slug")
    @Expose
    val slug: String? = null
)
