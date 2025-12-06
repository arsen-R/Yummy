package com.example.recipeapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    @SerialName("approved_at")
    val approved_at: Int? = null,
    @SerialName("aspect_ratio")
    val aspect_ratio: String? = null,
    @SerialName("beauty_url")
    val beauty_url: String? = null,
    @SerialName("buzz_id")
    val buzz_id: Int? = null,
    @SerialName("brand")
    val brand: BrandDto? = null,
    @SerialName("canonical_id")
    val canonical_id: String? = null,
    @SerialName("compilations")
    val compilations: List<CompilationDto>? = null,
    @SerialName("cook_time_minutes")
    val cook_time_minutes: Int? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("created_at")
    val created_at: Int? = null,
    @SerialName("credits")
    val credits: List<CreditDto>? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("draft_status")
    val draft_status: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("inspired_by_url")
    val inspired_by_url: String? = null,
    @SerialName("instructions")
    val instructions: List<InstructionDto>? = null,
    @SerialName("is_one_top")
    val is_one_top: Boolean? = null,
    @SerialName("is_shoppable")
    val is_shoppable: Boolean? = null,
    @SerialName("keywords")
    val keywords: String? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("num_servings")
    val num_servings: Int? = null,
    @SerialName("nutrition")
    val nutrition: NutritionDto? = null,
    @SerialName("nutrition_visibility")
    val nutrition_visibility: String? = null,
    @SerialName("original_video_url")
    val original_video_url: String? = null,
    @SerialName("prep_time_minutes")
    val prep_time_minutes: Int? = null,
    @SerialName("price")
    val price: PriceDto? = null,
    @SerialName("promotion")
    val promotion: String? = null,
    @SerialName("recipes")
    val recipes: List<RecipeDto>? = null,
    @SerialName("renditions")
    val renditions: List<RenditionDto>? = null,
    @SerialName("sections")
    val sections: List<SectionDto>? = null,
    @SerialName("seo_path")
    val seo_path: String? = null,
    @SerialName("seo_title")
    val seo_title: String? = null,
    @SerialName("servings_noun_plural")
    val servings_noun_plural: String? = null,
    @SerialName("servings_noun_singular")
    val servings_noun_singular: String? = null,
    @SerialName("show")
    val show: ShowDto? = null,
    @SerialName("show_id")
    val show_id: Int? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("tags")
    val tags: List<TagDto>? = null,
    @SerialName("thumbnail_alt_text")
    val thumbnail_alt_text: String? = null,
    @SerialName("thumbnail_url")
    val thumbnail_url: String? = null,
    @SerialName("tips_and_ratings_enabled")
    val tips_and_ratings_enabled: Boolean? = null,
    @SerialName("topics")
    val topics: List<TopicDto>? = null,
    @SerialName("total_time_minutes")
    val total_time_minutes: Int? = null,
    @SerialName("total_time_tier")
    val total_time_tier: TotalTimeTierDto? = null,
    @SerialName("updated_at")
    val updated_at: Int? = null,
    @SerialName("user_ratings")
    val user_ratings: UserRatingsDto? = null,
    @SerialName("video_ad_content")
    val video_ad_content: String? = null,
    @SerialName("video_id")
    val video_id: Int? = null,
    @SerialName("video_url")
    val video_url: String? = null,
    @SerialName("yields")
    val yields: String? = null,
)