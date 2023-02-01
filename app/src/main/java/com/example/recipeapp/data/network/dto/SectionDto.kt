package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SectionDto(
    @SerializedName("components")
    @Expose
    val components: List<ComponentDto?>? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("position")
    @Expose
    val position: Int? = null,
)