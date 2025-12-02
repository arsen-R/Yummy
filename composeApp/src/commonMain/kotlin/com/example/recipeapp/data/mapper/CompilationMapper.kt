package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.CompilationDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Compilation

class CompilationMapper(
    private val showMapper: ShowMapper
): BidirectionalMapper<Compilation?, CompilationDto?> {
    override fun toDomain(value: CompilationDto?): Compilation {
        return Compilation(
            approved_at = value?.approved_at,
            aspect_ratio = value?.aspect_ratio,
            beauty_url = value?.beauty_url,
            buzz_id = value?.buzz_id,
            canonical_id = value?.canonical_id,
            country = value?.country,
            created_at = value?.created_at,
            description = value?.description,
            draft_status = value?.draft_status,
            id = value?.id,
            is_shoppable = value?.is_shoppable,
            keywords = value?.keywords,
            language = value?.language,
            name = value?.name,
            promotion = value?.promotion,
            show = value?.show?.map { showMapper.toDomain(it) },
            slug = value?.slug,
            thumbnail_alt_text = value?.thumbnail_alt_text,
            thumbnail_url = value?.thumbnail_url,
            video_id = value?.video_id,
            video_url = value?.video_url,
        )
    }

    override fun fromDomain(value: Compilation?): CompilationDto {
        return CompilationDto(
            approved_at = value?.approved_at,
            aspect_ratio = value?.aspect_ratio,
            beauty_url = value?.beauty_url,
            buzz_id = value?.buzz_id,
            canonical_id = value?.canonical_id,
            country = value?.country,
            created_at = value?.created_at,
            description = value?.description,
            draft_status = value?.draft_status,
            id = value?.id,
            is_shoppable = value?.is_shoppable,
            keywords = value?.keywords,
            language = value?.language,
            name = value?.name,
            promotion = value?.promotion,
            show = value?.show?.map { showMapper.fromDomain(it) },
            slug = value?.slug,
            thumbnail_alt_text = value?.thumbnail_alt_text,
            thumbnail_url = value?.thumbnail_url,
            video_id = value?.video_id,
            video_url = value?.video_url,
        )
    }
}