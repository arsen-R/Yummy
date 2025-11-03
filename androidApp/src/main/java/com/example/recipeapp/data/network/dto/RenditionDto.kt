package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RenditionDto(
    @SerializedName("aspect")
    @Expose
    val aspect: String? = null,
    @SerializedName("bit_rate")
    @Expose
    val bit_rate: Int? = null,
    @SerializedName("container")
    @Expose
    val container: String? = null,
    @SerializedName("content_type")
    @Expose
    val content_type: String? = null,
    @SerializedName("duration")
    @Expose
    val duration: Int? = null,
    @SerializedName("file_size")
    @Expose
    val file_size: Int? = null,
    @SerializedName("height")
    @Expose
    val height: Int? = null,
    @SerializedName("maximum_bit_rate")
    @Expose
    val maximum_bit_rate: Int? = null,
    @SerializedName("minimum_bit_rate")
    @Expose
    val minimum_bit_rate: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("poster_url")
    @Expose
    val poster_url: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("width")
    @Expose
    val width: Int? = null,
)