package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompilationDto(
    @SerialName("approved_at")
    val approved_at: Int? = null,
    @SerialName("aspect_ratio")
    val aspect_ratio: String? = null,
    @SerialName("beauty_url")
    val beauty_url: String? = null,
    @SerialName("buzz_id")
    val buzz_id: Int? = null,
    @SerialName("canonical_id")
    val canonical_id: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("created_at")
    val created_at: Int? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("draft_status")
    val draft_status: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("is_shoppable")
    val is_shoppable: Boolean? = null,
    val keywords: String? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("promotion")
    val promotion: String? = null,
    @SerialName("show")
    val show: List<ShowDto>? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("thumbnail_alt_text")
    val thumbnail_alt_text: String? = null,
    @SerialName("thumbnail_url")
    val thumbnail_url: String? = null,
    @SerialName("video_id")
    val video_id: Int? = null,
    @SerialName("video_url")
    val video_url: String? = null,
)