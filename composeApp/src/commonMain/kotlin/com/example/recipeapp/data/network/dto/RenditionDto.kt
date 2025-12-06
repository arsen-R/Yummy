package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RenditionDto(
    @SerialName("aspect")
    val aspect: String? = null,
    @SerialName("bit_rate")
    val bit_rate: Int? = null,
    @SerialName("container")
    val container: String? = null,
    @SerialName("content_type")
    val content_type: String? = null,
    @SerialName("duration")
    val duration: Int? = null,
    @SerialName("file_size")
    val file_size: Int? = null,
    @SerialName("height")
    val height: Int? = null,
    @SerialName("maximum_bit_rate")
    val maximum_bit_rate: Int? = null,
    @SerialName("minimum_bit_rate")
    val minimum_bit_rate: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("poster_url")
    val poster_url: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("width")
    val width: Int? = null,
)