package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UnitDto(
    @SerializedName("abbreviation")
    @Expose
    val abbreviation: String? = null,
    @SerializedName("display_plural")
    @Expose
    val display_plural: String? = null,
    @SerializedName("display_singular")
    @Expose
    val display_singular: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("system")
    @Expose
    val system: String? = null,
)