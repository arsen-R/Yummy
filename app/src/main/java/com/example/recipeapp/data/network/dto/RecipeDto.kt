package com.example.recipeapp.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeDto(
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
    @SerializedName("brand")
    @Expose
    val brand: BrandDto? = null,
    @SerializedName("canonical_id")
    @Expose
    val canonical_id: String? = null,
    @SerializedName("compilations")
    @Expose
    val compilations: List<CompilationDto>? = null,
    @SerializedName("cook_time_minutes")
    @Expose
    val cook_time_minutes: Int? = null,
    @SerializedName("country")
    @Expose
    val country: String? = null,
    @SerializedName("created_at")
    @Expose
    val created_at: Int? = null,
    @SerializedName("credits")
    @Expose
    val credits: List<CreditDto>? = null,
    @SerializedName("description")
    @Expose
    val description: String? = null,
    @SerializedName("draft_status")
    @Expose
    val draft_status: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("inspired_by_url")
    @Expose
    val inspired_by_url: String? = null,
    @SerializedName("instructions")
    @Expose
    val instructions: List<InstructionDto>? = null,
    @SerializedName("is_one_top")
    @Expose
    val is_one_top: Boolean? = null,
    @SerializedName("is_shoppable")
    @Expose
    val is_shoppable: Boolean? = null,
    @SerializedName("keywords")
    @Expose
    val keywords: String? = null,
    @SerializedName("language")
    @Expose
    val language: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("num_servings")
    @Expose
    val num_servings: Int? = null,
    @SerializedName("nutrition")
    @Expose
    val nutrition: NutritionDto? = null,
    @SerializedName("nutrition_visibility")
    @Expose
    val nutrition_visibility: String? = null,
    @SerializedName("original_video_url")
    @Expose
    val original_video_url: String? = null,
    @SerializedName("prep_time_minutes")
    @Expose
    val prep_time_minutes: Int? = null,
    @SerializedName("price")
    @Expose
    val price: PriceDto? = null,
    @SerializedName("promotion")
    @Expose
    val promotion: String? = null,
    @SerializedName("renditions")
    @Expose
    val renditions: List<RenditionDto>? = null,
    @SerializedName("sections")
    @Expose
    val sections: List<SectionDto>? = null,
    @SerializedName("seo_path")
    @Expose
    val seo_path: String? = null,
    @SerializedName("seo_title")
    @Expose
    val seo_title: String? = null,
    @SerializedName("servings_noun_plural")
    @Expose
    val servings_noun_plural: String? = null,
    @SerializedName("servings_noun_singular")
    @Expose
    val servings_noun_singular: String? = null,
    @SerializedName("show")
    @Expose
    val show: ShowDto? = null,
    @SerializedName("show_id")
    @Expose
    val show_id: Int? = null,
    @SerializedName("slug")
    @Expose
    val slug: String? = null,
    @SerializedName("tags")
    @Expose
    val tags: List<TagDto>? = null,
    @SerializedName("thumbnail_alt_text")
    @Expose
    val thumbnail_alt_text: String? = null,
    @SerializedName("thumbnail_url")
    @Expose
    val thumbnail_url: String? = null,
    @SerializedName("tips_and_rating_enabled")
    @Expose
    val tips_and_ratings_enabled: Boolean? = null,
    @SerializedName("topics")
    @Expose
    val topics: List<TopicDto>? = null,
    @SerializedName("total_time_minutes")
    @Expose
    val total_time_minutes: Int? = null,
    @SerializedName("total_time_tier")
    @Expose
    val total_time_tier: TotalTimeTierDto? = null,
    @SerializedName("updated_at")
    @Expose
    val updated_at: Int? = null,
    @SerializedName("user_ratings")
    @Expose
    val user_ratings: UserRatingsDto? = null,
    @SerializedName("video_ad_content")
    @Expose
    val video_ad_content: String? = null,
    @SerializedName("video_id")
    @Expose
    val video_id: Int? = null,
    @SerializedName("video_url")
    @Expose
    val video_url: String? = null,
    @SerializedName("yields")
    @Expose
    val yields: String? = null,
)