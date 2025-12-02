package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.RenditionDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Rendition

class RenditionMapper: BidirectionalMapper<Rendition?, RenditionDto?> {
    override fun toDomain(value: RenditionDto?): Rendition {
        return Rendition(
            aspect = value?.aspect,
            bit_rate = value?.bit_rate,
            container = value?.container,
            content_type = value?.content_type,
            duration = value?.duration,
            file_size = value?.file_size,
            height = value?.height,
            maximum_bit_rate = value?.maximum_bit_rate,
            minimum_bit_rate = value?.minimum_bit_rate,
            name = value?.name,
            poster_url = value?.poster_url,
            url = value?.url,
            width = value?.width,
        )
    }

    override fun fromDomain(value: Rendition?): RenditionDto {
        return RenditionDto(
            aspect = value?.aspect,
            bit_rate = value?.bit_rate,
            container = value?.container,
            content_type = value?.content_type,
            duration = value?.duration,
            file_size = value?.file_size,
            height = value?.height,
            maximum_bit_rate = value?.maximum_bit_rate,
            minimum_bit_rate = value?.minimum_bit_rate,
            name = value?.name,
            poster_url = value?.poster_url,
            url = value?.url,
            width = value?.width,
        )
    }
}