package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("display_name")
    @Expose
    val display_name: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("type")
    @Expose
    val type: String? = null,
)