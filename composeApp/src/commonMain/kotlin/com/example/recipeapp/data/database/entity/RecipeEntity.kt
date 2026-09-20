package com.example.recipeapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_result_table")
data class RecipeEntity(
    val approved_at: Int? = null,
    val aspect_ratio: String? = null,
    val beauty_url: String? = null,
    val buzz_id: Int? = null,
    val canonical_id: String? = null,
    val cook_time_minutes: Int? = null,
    val country: String? = null,
    val created_at: Int? = null,
    val description: String? = null,
    val draft_status: String? = null,
    @PrimaryKey
    val id: Int? = null,
    val inspired_by_url: String? = null,
    val is_one_top: Boolean? = null,
    val is_shoppable: Boolean? = null,
    val keywords: String? = null,
    val language: String? = null,
    val name: String? = null,
    val num_servings: Int? = null,
    val nutrition_visibility: String? = null,
    val original_video_url: String? = null,
    val prep_time_minutes: Int? = null,
    val promotion: String? = null,
    val seo_path: String? = null,
    val seo_title: String? = null,
    val servings_noun_plural: String? = null,
    val servings_noun_singular: String? = null,
    val show_id: Int? = null,
    val slug: String? = null,
    val thumbnail_alt_text: String? = null,
    val thumbnail_url: String? = null,
    val tips_and_ratings_enabled: Boolean? = null,
    val total_time_minutes: Int? = null,
    val updated_at: Int? = null,
    val video_ad_content: String? = null,
    val video_id: Int? = null,
    val video_url: String? = null,
    val yields: String? = null,
)