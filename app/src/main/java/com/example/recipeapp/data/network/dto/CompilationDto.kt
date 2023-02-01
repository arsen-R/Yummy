package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CompilationDto(
    @SerializedName("approved_at")
    @Expose
    val approved_at: Int? = null,
    @SerializedName("aspect_ratio")
    @Expose
    val aspect_ratio: String? = null,
    @SerializedName("beauty_url")
    @Expose
    val beauty_url: String? = null,
    @SerializedName("buzz_id")
    @Expose
    val buzz_id: Int? = null,
    @SerializedName("canonical_id")
    @Expose
    val canonical_id: String? = null,
    @SerializedName("country")
    @Expose
    val country: String? = null,
    @SerializedName("created_at")
    @Expose
    val created_at: Int? = null,
    @SerializedName("description")
    @Expose
    val description: String? = null,
    @SerializedName("draft_status")
    @Expose
    val draft_status: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("is_shoppable")
    @Expose
    val is_shoppable: Boolean? = null,
    val keywords: String? = null,
    @SerializedName("language")
    @Expose
    val language: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("promotion")
    @Expose
    val promotion: String? = null,
    @SerializedName("show")
    @Expose
    val show: List<ShowDto>? = null,
    @SerializedName("slug")
    @Expose
    val slug: String? = null,
    @SerializedName("thumbnail_alt_text")
    @Expose
    val thumbnail_alt_text: String? = null,
    @SerializedName("thumbnail_url")
    @Expose
    val thumbnail_url: String? = null,
    @SerializedName("video_id")
    @Expose
    val video_id: Int? = null,
    @SerializedName("video_url")
    @Expose
    val video_url: String? = null,
)